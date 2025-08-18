package com.example.stemshop.controller;

import com.example.stemshop.dto.request.product.ProductAddRequest;
import com.example.stemshop.dto.request.product.ProductUpdateRequest;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.services.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> getProductByArticle(@PathVariable String sku) {
        return ResponseEntity.ok(productService.getProductByArticle(sku));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductAddRequest request) {
        productService.addProduct(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{sku}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable String sku,
            @Valid @RequestBody ProductUpdateRequest request
    ) {
        productService.updateProduct(sku, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }
}
