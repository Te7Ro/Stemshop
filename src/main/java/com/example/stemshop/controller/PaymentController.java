package com.example.stemshop.controller;

import com.example.stemshop.data.enums.PaymentStatus;
import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.services.order.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Оплата")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Обновление статуса оплаты, требуется права Админа или Оператора")
    @PatchMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(
            @PathVariable Long id,
            @RequestBody @Valid PaymentStatus status
    ){
        return ResponseEntity.ok(paymentService.updatePaymentStatus(id, status));
    }

    @Operation(summary = "Подтверждение оплаты, происходит автоматический")
    @GetMapping("/success/{orderId}")
    public ResponseEntity<Void> success(@PathVariable Long orderId) {
        paymentService.confirmPayment(orderId);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Отклонение оплаты, происходит автоматический")
    @GetMapping("/canceled/{orderId}")
    public ResponseEntity<Void> canceled(@PathVariable Long orderId) {
        paymentService.cancelPayment(orderId);
        return ResponseEntity.ok().build();
    }
}
