package com.example.stemshop.controller;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.models.Cart;
import com.example.stemshop.services.cart.CartService;
import com.example.stemshop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.ok(cartService.getCart(userService.getUser().getId()));
    }

    @PutMapping
    public ResponseEntity<Void> updateCart(@RequestBody List<Long> productIds) {
        cartService.updateCart(productIds, userService.getUser().getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart(userService.getUser().getId());
        return ResponseEntity.noContent().build();
    }

}
