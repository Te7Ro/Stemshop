package com.example.stemshop.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private String article;
    private int price;
    private String photo;
    private String description;
    private String technicalCharacteristics;
    private int stock;
    private String brand;
    private double rating;
    private int ratingCount;

}
