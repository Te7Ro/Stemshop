package com.example.stemshop.dto.response.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    @Schema(description = "Id товара", example = "1")
    private Long id;

    @Schema(description = "Имя товара", example = "Lego")
    private String name;

    @Schema(description = "Артикул товара", example = "ART1001")
    private String sku;

    @Schema(description = "Цена товара", example = "50000")
    private int price;

    @Schema(description = "Ссылка на фото товара", example = "https://example.com/images/kb2025.jpg")
    private String photo;

    @Schema(description = "Описание товара", example = "Набор для участия в международных соревнованиях, для начальной школы, FLL Explore Set")
    private String description;

    @Schema(description = "Техническая характеристика", example = "Switches: Red, RGB: Yes, Layout: ANSI")
    private String technicalCharacteristics;

    @Schema(description = "Количество товара", example = "50")
    private int stock;

    @Schema(description = "Бренд", example = "Lego")
    private String brand;

    @Schema(description = "Рейтинг товара", example = "4.5")
    private double rating;

    @Schema(description = "Количество отзывов", example = "10")
    private int ratingCount;

}
