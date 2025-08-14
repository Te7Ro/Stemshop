package com.example.stemshop.services.order;

import com.example.stemshop.data.enums.OrderStatus;
import com.example.stemshop.dto.request.order.OrderStatusChangeRequest;
import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.exceptions.OrderException;
import com.example.stemshop.exceptions.ProductException;
import com.example.stemshop.models.*;
import com.example.stemshop.repositories.*;
import com.example.stemshop.services.auth.AuthService;
import com.example.stemshop.services.cart.CartService;
import com.example.stemshop.util.OrderMapper;
import com.stripe.exception.StripeException;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final AuthService authService;
    private final PaymentService paymentService;
    private final CouponService couponService;

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public String makeOrder(@Nullable String couponCode) throws StripeException {
        final User user = userRepository.findById(authService.getUserId())
                .orElseThrow(() -> new OrderException("Пользователь не найден"));
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        List<Cart> carts = cartRepository.findAllByUser(user)
                .orElseThrow(() -> new OrderException("Корзина пустая"));

        int totalPrice = 0;
        for (Cart cart : carts) {
            final Product product = productRepository.findById(cart.getProduct().getId())
                    .orElseThrow(() -> new ProductException("Товар не найден"));
            totalPrice += product.getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItemRepository.save(orderItem);
        }

        if(couponCode != null) {
            totalPrice = couponService.applyCoupon(couponCode, order);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);


        return paymentService.createCheckoutSession(order);
    }

    public OrderResponse changeOrderStatus(Long orderId, OrderStatusChangeRequest request) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Заказ не найден"));
        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new OrderException("Нельзя изменить статус завершённого или отменённого заказа");
        }
        order.setStatus(request.getStatus());
        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    public List<OrderResponse> getOrdersByUser() {
        final User user = userRepository.findById(authService.getUserId())
                .orElseThrow(() -> new OrderException("Пользователь не найден"));
        List<Order> orders = orderRepository.findAllByUser(user);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(orderMapper.toResponse(order));
        }
        return orderResponses;
    }
}
