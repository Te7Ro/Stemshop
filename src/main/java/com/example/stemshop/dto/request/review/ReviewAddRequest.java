package com.example.stemshop.dto.request.review;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewAddRequest {
    @Schema(description = "Артикул товара", example = "ART1001")
    @NotBlank(message = "Артикул товара не может быть пустым")
    @Size(max = 50, message = "Артикул товара не должен превышать 50 символов")
    private String productSku;

    @Schema(description = "Рейтинг товара", example = "4")
    @Min(value = 1, message = "Рейтинг должен быть не меньше 1")
    @Max(value = 5, message = "Рейтинг должен быть не больше 5")
    private Integer rating;

    @Schema(description = "Комментарии к товару", example = "Товар очень понравился, но есть один минус")
    @NotBlank(message = "Комментарий не может быть пустым")
    @Size(max = 1000, message = "Комментарий не должен превышать 1000 символов")
    private String comment;
}
