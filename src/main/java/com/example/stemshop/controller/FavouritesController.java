package com.example.stemshop.controller;

import com.example.stemshop.dto.response.favourites.FavouritesResponse;
import com.example.stemshop.services.favourites.FavouritesService;
import com.example.stemshop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;
    private final UserService userService;

    private Long currentUserId() {
        return userService.getUser().getId();
    }

    @GetMapping
    public FavouritesResponse getFavourites(){
        return favouritesService.getFavorites(currentUserId());
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(@PathVariable Long productId,
                                        @RequestParam(defaultValue = "1") int qty) {
        favouritesService.addItem(currentUserId(), productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long productId) {
        favouritesService.removeItem(currentUserId(), productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        favouritesService.clear(currentUserId());
        return ResponseEntity.ok().build();
    }
}
