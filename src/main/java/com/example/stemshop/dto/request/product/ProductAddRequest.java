package com.example.stemshop.dto.request.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductAddRequest {

    @Schema(description = "Имя товара", example = "LEGO Education  Базовый набор SPIKE Start Спайк Старт 45345")
    @NotBlank(message = "Название товара обязательно")
    @Size(min = 2, max = 255, message = "Название должно быть от 2 до 255 символов")
    private String name;

    @Schema(description = "Артикул товара", example = "ART1001")
    @NotBlank(message = "Артикул обязателен")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Артикул может содержать только буквы, цифры, дефис и нижнее подчёркивание")
    private String article;

    @Schema(description = "Цена товара", example = "50000")
    @Positive(message = "Цена должна быть больше 0")
    private int price;

    @Schema(description = "Ссылка на фото", example = "https://example.com/images/kb2025.jpg")
    @NotBlank(message = "Фото обязательно")
    @Pattern(regexp = "^(http|https)://.*$", message = "Фото должно быть ссылкой")
    private String photo;

    @Schema(description = "Описание товара", example = "Набор для участия в международных соревнованиях, для начальной школы, FLL Explore Set")
    @NotBlank(message = "Описание обязательно")
    @Size(min = 10, message = "Описание должно содержать хотя бы 10 символов")
    private String description;

    @Schema(description = "Техническая характеристика товара", example = "Switches: Red, RGB: Yes, Layout: ANSI")
    @NotBlank(message = "Технические характеристики обязательны")
    @Size(min = 5, message = "Технические характеристики должны быть более 5 символов")
    private String technicalCharacteristics;

    @Schema(description = "Количество на складе", example = "50")
    @PositiveOrZero(message = "Количество на складе не может быть отрицательным")
    private int stock;

    @Schema(description = "Бренд товара", example = "Lego")
    @NotBlank(message = "Бренд обязателен")
    private String brand;
}