package com.example.stemshop.controller;

import com.example.stemshop.models.Product;
import com.example.stemshop.services.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("api/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean onlyAvailable,
            @RequestParam(required = false) String sortBy
    ) {
        return ResponseEntity.ok(catalogService.getFilteredProducts(brand, minPrice, maxPrice, onlyAvailable, sortBy));
    }
}
