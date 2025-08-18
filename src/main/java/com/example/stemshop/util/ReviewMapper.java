package com.example.stemshop.util;

import com.example.stemshop.dto.response.review.ReviewResponse;
import com.example.stemshop.models.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public ReviewResponse toResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setUserName(review.getUser().getFullName());
        response.setRating(review.getRating());
        response.setComment(review.getComment());

        return response;
    }
}
