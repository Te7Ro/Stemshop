package com.example.stemshop.dto.request.order;

import com.example.stemshop.data.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull(message = "Обязательно выбери способ оплаты")
    private PaymentMethod paymentMethod;
}
