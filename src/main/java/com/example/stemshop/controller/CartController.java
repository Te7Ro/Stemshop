package com.example.stemshop.controller;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.services.cart.CartService;
import com.example.stemshop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    private Long currentUserId() {
        return userService.getUser().getId();
    }

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart(currentUserId());
    }

    @PostMapping("/items/{productId}")
    public ResponseEntity<Void> addItem(@PathVariable Long productId,
                                        @RequestParam(defaultValue = "1") int qty) {
        cartService.addItem(currentUserId(), productId, qty);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/items/{productId}")
    public ResponseEntity<Void> setQuantity(@PathVariable Long productId,
                                            @RequestParam int qty) {
        cartService.setQuantity(currentUserId(), productId, qty);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long productId) {
        cartService.removeItem(currentUserId(), productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        cartService.clear(currentUserId());
        return ResponseEntity.ok().build();
    }
}