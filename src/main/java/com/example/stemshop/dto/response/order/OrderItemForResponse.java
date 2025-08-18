package com.example.stemshop.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemForResponse {
    private Long productId;
    private String name;
    private int price;
    private Integer quantity;
}
