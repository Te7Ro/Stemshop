package com.example.stemshop.controller;

import com.example.stemshop.dto.response.favourites.FavouritesResponse;
import com.example.stemshop.services.favourites.FavouritesService;
import com.example.stemshop.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
@Tag(name = "Избранные", description = "Избранные через Redis, данные исчезают если не менять в течении 30 суток")
public class FavouritesController {

    private final FavouritesService favouritesService;
    private final UserService userService;

    private Long currentUserId() {
        return userService.getUser().getId();
    }

    @Operation(summary = "Получение списка избранных")
    @GetMapping
    public FavouritesResponse getFavourites(){
        return favouritesService.getFavorites(currentUserId());
    }

    @Operation(summary = "Добавление товара в избранные")
    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(@PathVariable Long productId,
                                        @RequestParam(defaultValue = "1") int qty) {
        favouritesService.addItem(currentUserId(), productId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удаление товара из избранных")
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long productId) {
        favouritesService.removeItem(currentUserId(), productId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Очистка списка избранных")
    @DeleteMapping
    public ResponseEntity<Void> clear() {
        favouritesService.clear(currentUserId());
        return ResponseEntity.ok().build();
    }
}
