package com.example.stemshop.dto.request.product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductUpdateRequest {
    private String name;

    @Positive(message = "Цена должна быть положительной")
    private Integer price;

    private String photo;
    private String description;
    private String technicalCharacteristics;

    @PositiveOrZero(message = "Количество на складе не может быть отрицательным")
    private Integer stock;

    private String brand;
}
