package com.example.stemshop.dto.response.order;

import com.example.stemshop.data.enums.OrderStatus;
import lombok.Data;
import java.util.List;

@Data
public class OrderResponse {
    private Long orderId;
    private String username;
    private Integer totalPrice;
    private OrderStatus status;
    private List<OrderItemForResponse> items;
}
