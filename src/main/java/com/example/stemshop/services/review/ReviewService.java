package com.example.stemshop.services.review;

import com.example.stemshop.dto.request.review.ReviewAddRequest;
import com.example.stemshop.dto.response.review.ReviewResponse;
import com.example.stemshop.exceptions.NotFoundException;
import com.example.stemshop.exceptions.ReviewException;
import com.example.stemshop.models.Product;
import com.example.stemshop.models.Review;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.OrderRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.repositories.ReviewRepository;
import com.example.stemshop.services.user.UserService;
import com.example.stemshop.util.ReviewMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ReviewMapper reviewMapper;
    private final UserService userService;

    @Transactional
    public void makeReview(ReviewAddRequest request) {
        final User user = userService.getUser();
        final Product product = productRepository.findBySku(request.getProductSku())
                .orElseThrow(() -> new NotFoundException("Товар не найден"));

        if(!orderRepository.existsProductByUser(user.getId(), product.getId())) {
            throw new ReviewException("Вы не приобретали этот товар для отзыва");
        }

        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        reviewRepository.save(review);

        Double oldRating = product.getRating();
        Integer ratingCount = product.getRatingCount();

        Double newRating = ((oldRating * ratingCount) + request.getRating()) / (ratingCount + 1);
        product.setRating(newRating);
        product.setRatingCount(ratingCount+1);
        productRepository.save(product);
    }

    public List<ReviewResponse> getReviewByProduct(String productSku) {
        final Product product = productRepository.findBySku(productSku)
                .orElseThrow(() -> new NotFoundException("Товар не найден"));

        List<Review> reviews = reviewRepository.findAllByProduct(product);

        List<ReviewResponse> responses = new ArrayList<>();

        for(Review review : reviews) {
            responses.add(reviewMapper.toResponse(review));
        }

        return responses;
    }
}
