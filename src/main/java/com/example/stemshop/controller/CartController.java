package com.example.stemshop.controller;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.models.Cart;
import com.example.stemshop.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PutMapping
    public ResponseEntity<Void> updateCart(@RequestBody List<Long> productIds) {
        cartService.updateCart(productIds);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }

}
