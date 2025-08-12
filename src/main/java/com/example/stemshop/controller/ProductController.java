package com.example.stemshop.controller;

import com.example.stemshop.dto.request.ProductAddRequest;
import com.example.stemshop.dto.request.ProductUpdateRequest;
import com.example.stemshop.dto.response.ProductResponse;
import com.example.stemshop.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductAddRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PostMapping("/{article}/update")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String article,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        return ResponseEntity.ok(productService.updateProduct(article, request));
    }

    @PostMapping("/{article}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable String article) {
        return ResponseEntity.ok(productService.deleteProduct(article));
    }
}
