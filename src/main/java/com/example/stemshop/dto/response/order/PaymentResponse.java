package com.example.stemshop.dto.response.order;

import com.example.stemshop.data.enums.PaymentMethod;
import com.example.stemshop.data.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
    @Schema(description = "Id заказа", example = "1")
    private Long orderId;

    @Schema(description = "Конечная цена", example = "150000")
    private int amount;

    @Schema(description = "Метод оплаты", example = "CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Статус оплаты", example = "PAID")
    private PaymentStatus paymentStatus;

    @Schema(description = "Дата создания записи", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;
}
