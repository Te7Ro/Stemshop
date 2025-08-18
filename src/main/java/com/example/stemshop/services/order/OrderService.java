package com.example.stemshop.services.order;

import com.example.stemshop.data.enums.OrderStatus;
import com.example.stemshop.dto.request.order.OrderStatusChangeRequest;
import com.example.stemshop.dto.response.cart.CartItem;
import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.exceptions.OrderException;
import com.example.stemshop.exceptions.ProductException;
import com.example.stemshop.models.*;
import com.example.stemshop.repositories.*;
import com.example.stemshop.services.auth.AuthService;
import com.example.stemshop.services.cart.CartService;
import com.example.stemshop.services.coupon.CouponService;
import com.example.stemshop.services.user.UserService;
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
    private final PaymentService paymentService;
    private final CouponService couponService;

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;
    private final UserService userService;
    private final CartService cartService;

    @Transactional
    public String makeOrder(@Nullable String couponCode) throws StripeException {
        final User user = userService.getUser();
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        CartResponse cart = cartService.getCart(user.getId());
        int totalPrice = cart.getTotalAmount();

        if(couponCode != null) {
            totalPrice = totalPrice - couponService.applyCoupon(couponCode, order);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        for (CartItem item : cart.getItems()) {
            final Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductException("Товар не найден"));
            if (product.getStock() < item.getQuantity()) {
                throw new ProductException("Недостаточно товара на складе: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItemRepository.save(orderItem);
        }


        return paymentService.createCheckoutSession(order);
    }

    public void changeOrderStatus(Long orderId, OrderStatusChangeRequest request) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Заказ не найден"));
        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new OrderException("Нельзя изменить статус завершённого или отменённого заказа");
        }
        order.setStatus(request.getStatus());
        orderRepository.save(order);
    }

    public List<OrderResponse> getOrdersByUser() {
        final User user = userService.getUser();
        List<Order> orders = orderRepository.findAllByUser(user);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(orderMapper.toResponse(order));
        }
        return orderResponses;
    }
}
