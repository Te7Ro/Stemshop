package com.example.stemshop.controller;

import com.example.stemshop.dto.request.review.ReviewAddRequest;
import com.example.stemshop.dto.response.review.ReviewResponse;
import com.example.stemshop.services.review.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Tag(name = "Отзывы на товары")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "Оставить отзыв на товар, работает только если пользователь купил товар")
    @PostMapping("/add")
    public ResponseEntity<Void> addReview(
            @RequestBody @Valid ReviewAddRequest reviewAddRequest
    ) {
        reviewService.makeReview(reviewAddRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получение списка всех отзывов на товар")
    @GetMapping("/{productArticle}")
    public ResponseEntity<List<ReviewResponse>> getReviewByProductArticle(
            @PathVariable String productArticle
    ) {
        return ResponseEntity.ok(reviewService.getReviewByProduct(productArticle));
    }
}
