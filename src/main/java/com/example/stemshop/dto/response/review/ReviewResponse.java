package com.example.stemshop.dto.response.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewResponse {
    @Schema(description = "Имя человека оставивший отзыв", example = "Имя Фамилия")
    private String userName;

    @Schema(description = "Рейтинг", example = "4")
    private int rating;

    @Schema(description = "Комментарий к отзыву", example = "Все понравилось, но есть один минус")
    private String comment;
}
