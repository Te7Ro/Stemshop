package com.example.stemshop.dto.response.favourites;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FavouritesResponse {
    private List<FavouritesItem> items;
}
