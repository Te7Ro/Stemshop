package com.example.stemshop.dto.request.order;

import com.example.stemshop.data.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderStatusChangeRequest {
    @NotNull(message = "Статус заказа обязателен")
    private OrderStatus status;
}
