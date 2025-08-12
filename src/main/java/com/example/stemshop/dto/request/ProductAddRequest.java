package com.example.stemshop.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductAddRequest {

    @NotBlank(message = "Название товара обязательно")
    @Size(min = 2, max = 255, message = "Название должно быть от 2 до 255 символов")
    private String name;

    @NotBlank(message = "Артикул обязателен")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Артикул может содержать только буквы, цифры, дефис и нижнее подчёркивание")
    private String article;

    @Positive(message = "Цена должна быть больше 0")
    private int price;

    @NotBlank(message = "Фото обязательно")
    @Pattern(regexp = "^(http|https)://.*$", message = "Фото должно быть ссылкой")
    private String photo;

    @NotBlank(message = "Описание обязательно")
    @Size(min = 10, message = "Описание должно содержать хотя бы 10 символов")
    private String description;

    @NotBlank(message = "Технические характеристики обязательны")
    @Size(min = 5, message = "Технические характеристики должны быть более 5 символов")
    private String technicalCharacteristics;

    @PositiveOrZero(message = "Количество на складе не может быть отрицательным")
    private int stock;

    @NotBlank(message = "Бренд обязателен")
    private String brand;
}