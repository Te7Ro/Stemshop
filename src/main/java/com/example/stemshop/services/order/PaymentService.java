package com.example.stemshop.services.order;

import com.example.stemshop.data.enums.OrderStatus;
import com.example.stemshop.data.enums.PaymentMethod;
import com.example.stemshop.data.enums.PaymentStatus;
import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.exceptions.NotFoundException;
import com.example.stemshop.exceptions.OrderException;
import com.example.stemshop.models.Order;
import com.example.stemshop.models.OrderItem;
import com.example.stemshop.models.Payment;
import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.OrderItemRepository;
import com.example.stemshop.repositories.OrderRepository;
import com.example.stemshop.repositories.PaymentRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.services.cart.CartService;
import com.example.stemshop.util.PaymentMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final CartService cartService;

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    private final PaymentMapper paymentMapper;

    @Value("${stripe.success-url}")
    private String successUrl;

    @Value("${stripe.cancel-url}")
    private String cancelUrl;

    public String createCheckoutSession(Order order) throws StripeException {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentMethod(PaymentMethod.CARD);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);


        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSuccessUrl(successUrl+order.getId())
                .setCancelUrl(cancelUrl+order.getId())
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount((long)order.getTotalPrice())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Оплата заказа №" + order.getId())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();
        Session session = Session.create(params);
        return session.getUrl();

    }

    public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatus paymentStatus) {
        final Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Транзакция не найдена"));

        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);

        return paymentMapper.toResponse(payment);
    }

    @Transactional
    public PaymentResponse confirmPayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Заказ не найден"));

        if (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new OrderException("Оплата уже обработана");
        }

        // Уменьшаем количество товаров
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new OrderException("Недостаточно товара на складе: " + product.getName());
            }
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        // Сохраняем Payment
        Payment payment = paymentRepository.findByOrder(order)
                .orElseThrow(() -> new NotFoundException("Оплата не найдена"));
        payment.setPaymentStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);

        // Меняем статус заказа
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);

        // Чистим корзину
        cartService.clear(order.getId());

        return paymentMapper.toResponse(payment);
    }

    public PaymentResponse cancelPayment(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Заказ не найден"));

        if (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new OrderException("Оплата уже обработана");
        }

        Payment payment = paymentRepository.findByOrder(order)
                .orElseThrow(() -> new NotFoundException("Оплата не найдена"));
        payment.setPaymentStatus(PaymentStatus.FAILED);
        paymentRepository.save(payment);

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        return paymentMapper.toResponse(payment);
    }

}
