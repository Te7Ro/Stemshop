package com.example.stemshop.util;

import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.models.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setOrder(payment.getOrder());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setCreatedAt(payment.getCreatedAt());
        return response;
    }
}
