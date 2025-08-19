package com.example.stemshop.dto.response.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    @Schema(description = "Список товаров")
    private List<CartItem> items;

    @Schema(description = "Общее количество товаров", example = "10")
    private int totalQuantity;

    @Schema(description = "Конечная цена", example = "150000")
    private int totalAmount;
}
