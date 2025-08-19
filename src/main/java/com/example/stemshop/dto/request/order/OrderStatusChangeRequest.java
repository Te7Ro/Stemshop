package com.example.stemshop.dto.request.order;

import com.example.stemshop.data.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusChangeRequest {
    @Schema(description = "Статус заказа", example = "PENDING")
    @NotNull(message = "Статус заказа обязателен")
    private OrderStatus status;
}
