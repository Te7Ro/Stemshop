package com.example.stemshop.controller;

import com.example.stemshop.dto.request.order.OrderStatusChangeRequest;
import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.services.order.OrderService;
import com.stripe.exception.StripeException;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrdersByUser());
    }

    @PostMapping("/add")
    public ResponseEntity<String> createOrder(@RequestParam @Nullable String couponCode) throws StripeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.makeOrder(couponCode));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderStatusChangeRequest request
    ) {
        orderService.changeOrderStatus(id, request);
        return ResponseEntity.ok().build();
    }


}
