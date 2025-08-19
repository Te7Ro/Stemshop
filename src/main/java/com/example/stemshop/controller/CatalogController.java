package com.example.stemshop.controller;

import com.example.stemshop.dto.response.catalog.CatalogResponse;
import com.example.stemshop.services.catalog.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/catalog")
@RequiredArgsConstructor
@Tag(name = "Каталог товаров")
public class CatalogController {
    private final CatalogService catalogService;

    @Operation(summary = "Получение списка всех товаров(sortBy: NEWEST, PRICE_ASC, PRICE_DESC, RATING)")
    @GetMapping
    public ResponseEntity<CatalogResponse> getProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean onlyAvailable,
            @RequestParam(required = false) String sortBy
    ) {
        return ResponseEntity.ok(catalogService.getFilteredProducts(brand, minPrice, maxPrice, onlyAvailable, sortBy));
    }
}
