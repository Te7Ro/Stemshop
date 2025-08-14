package com.example.stemshop.dto.response.review;

import lombok.Data;

@Data
public class ReviewResponse {
    private String userName;
    private int rating;
    private String comment;
}
