package com.example.stemshop.dto.response.catalog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogItem {

    @Schema(description = "ID товара", example = "1")
    private Long productId;

    @Schema(description = "Имя товара", example = "FIRST LEGO набор")
    private String name;

    @Schema(description = "Артикул товара", example = "ART1001")
    private String sku;

    @Schema(description = "Цена товара", example = "50000")
    private int price;

    @Schema(description = "Ссылка на фото товара", example = "https://example.com/images/kb2025.jpg")
    private String photo;

    @Schema(description = "Описание товара", example = "Набор для участия в международных соревнованиях, для начальной школы, FLL Explore Set")
    private String description;

    @Schema(description = "Количество товара", example = "50")
    private int stock;

    @Schema(description = "Бренд", example = "Lego")
    private String brand;

    @Schema(description = "Рейтинг", example = "3.5")
    private Double rating;
}
