package com.example.stemshop.controller;

import com.example.stemshop.data.enums.PaymentStatus;
import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.services.order.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam @Valid PaymentStatus status
    ){
        return ResponseEntity.ok(paymentService.updatePaymentStatus(id, status));
    }
}
