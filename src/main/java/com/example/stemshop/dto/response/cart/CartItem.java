package com.example.stemshop.dto.response.cart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Schema(description = "ID товара", example = "1")
    private Long productId;

    @Schema(description = "Имя товара", example = "FIRST® LEGO® League Explore")
    private String name;

    @Schema(description = "Цена товара", example = "50000")
    private int price;

    @Schema(description = "Количество товара", example = "3")
    private Integer quantity;
}
