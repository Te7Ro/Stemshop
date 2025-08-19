package com.example.stemshop.dto.response.favourites;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FavouritesResponse {
    @Schema(description = "Список товаров")
    private List<FavouritesItem> items;
}
