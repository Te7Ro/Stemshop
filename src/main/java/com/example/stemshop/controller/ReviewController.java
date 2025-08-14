package com.example.stemshop.controller;

import com.example.stemshop.dto.request.review.ReviewAddRequest;
import com.example.stemshop.dto.response.review.ReviewResponse;
import com.example.stemshop.services.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Void> addReview(
            @RequestBody @Valid ReviewAddRequest reviewAddRequest
    ) {
        reviewService.makeReview(reviewAddRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productArticle}")
    public ResponseEntity<List<ReviewResponse>> getReviewByProductArticle(
            @PathVariable String productArticle
    ) {
        return ResponseEntity.ok(reviewService.getReviewByProduct(productArticle));
    }
}
