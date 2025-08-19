package com.example.stemshop.dto.response.favourites;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavouritesItem {
    @Schema(description = "ID товара", example = "1")
    private Long productId;

    @Schema(description = "Имя товара", example = "Lego набор")
    private String name;

    @Schema(description = "Цена товара", example = "50000")
    private int price;
}
