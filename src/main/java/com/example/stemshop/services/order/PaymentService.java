package com.example.stemshop.services.order;

import com.example.stemshop.dto.response.order.PaymentResponse;
import com.example.stemshop.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentResponse pay(String accessToken) {

    }

}
