package com.example.stemshop.dto.response.order;

import com.example.stemshop.data.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class OrderResponse {
    @Schema(description = "Id заказа", example = "1")
    private Long orderId;

    @Schema(description = "Имя заказчика", example = "Имя фамилия")
    private String username;

    @Schema(description = "Конечная цена", example = "150000")
    private Integer totalPrice;

    @Schema(description = "Статус заказа", example = "COMPLETED")
    private OrderStatus status;

    @Schema(description = "Список товаров")
    private List<OrderItemForResponse> items;
}
