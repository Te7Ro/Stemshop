package com.example.stemshop.controller;

import com.example.stemshop.dto.request.order.OrderStatusChangeRequest;
import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.services.order.OrderService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Заказы")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Получение списка всех заказов")
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getOrdersByUser());
    }

    @Operation(summary = "Создание заказа, берет все товары из корзины")
    @PostMapping("/add")
    public ResponseEntity<String> createOrder(@RequestParam @Nullable String couponCode) throws StripeException {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.makeOrder(couponCode));
    }

    @Operation(summary = "Обновление статуса заказа, требуется права Админа или Оператора")
    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderStatusChangeRequest request
    ) {
        orderService.changeOrderStatus(id, request);
        return ResponseEntity.ok().build();
    }


}
