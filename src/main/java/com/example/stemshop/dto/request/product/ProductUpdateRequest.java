package com.example.stemshop.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    @Schema(description = "Имя товара", example = "LEGO Education  Базовый набор SPIKE Start Спайк Старт 45345")
    private String name;

    @Schema(description = "Цена товара", example = "50000")
    @Positive(message = "Цена должна быть положительной")
    private Integer price;

    @Schema(description = "Ссылка на фото", example = "https://example.com/images/kb2025.jpg")
    private String photo;

    @Schema(description = "Описание товара", example = "Набор для участия в международных соревнованиях, для начальной школы, FLL Explore Set")
    private String description;

    @Schema(description = "Техническая характеристика товара", example = "Switches: Red, RGB: Yes, Layout: ANSI")
    private String technicalCharacteristics;

    @Schema(description = "Количество на складе", example = "50")
    @PositiveOrZero(message = "Количество на складе не может быть отрицательным")
    private Integer stock;

    @Schema(description = "Бренд товара", example = "Lego")
    private String brand;
}
