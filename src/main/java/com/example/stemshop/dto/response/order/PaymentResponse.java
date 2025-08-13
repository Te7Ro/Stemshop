package com.example.stemshop.dto.response.order;

import com.example.stemshop.data.enums.PaymentMethod;
import com.example.stemshop.data.enums.PaymentStatus;
import com.example.stemshop.models.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    private Order order;
    private int amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private LocalDateTime createdAt;
}
