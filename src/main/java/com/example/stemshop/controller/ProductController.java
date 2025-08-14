package com.example.stemshop.controller;

import com.example.stemshop.dto.request.product.ProductAddRequest;
import com.example.stemshop.dto.request.product.ProductUpdateRequest;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.services.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{article}")
    public ResponseEntity<ProductResponse> getProductByArticle(@PathVariable String article) {
        return ResponseEntity.ok(productService.getProductByArticle(article));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductAddRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
    }

    @PatchMapping("/{article}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String article,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        return ResponseEntity.ok(productService.updateProduct(article, request));
    }

    @DeleteMapping("/{article}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String article) {
        productService.deleteProduct(article);
        return ResponseEntity.noContent().build();
    }
}
