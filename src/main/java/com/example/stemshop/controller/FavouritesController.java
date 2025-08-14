package com.example.stemshop.controller;

import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.services.favourites.FavouritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getFavourites() {
        return ResponseEntity.ok(favouritesService.getFavourites());
    }

    @PutMapping
    public ResponseEntity<Void> updateFavourites(@RequestBody List<Long> productIds) {
        favouritesService.updateFavourites(productIds);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearFavourites() {
        favouritesService.clearFavourites();
        return ResponseEntity.noContent().build();
    }
}
