package com.example.stemshop.dto.request.order;

import com.example.stemshop.data.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @Schema(description = "Метод оплаты", example = "CARD0")
    @NotNull(message = "Обязательно выбери способ оплаты")
    private PaymentMethod paymentMethod;
}
