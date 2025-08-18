package com.example.stemshop.dto.response.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogItem {
    private Long productId;
    private String name;
    private String sku;
    private int price;
    private String photo;
    private String description;
    private String category;
    private int stock;
    private String brand;
    private Double rating;
}
