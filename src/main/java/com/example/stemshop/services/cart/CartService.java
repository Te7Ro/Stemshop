package com.example.stemshop.services.cart;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.models.Cart;

import java.util.List;

public interface CartService {
    CartResponse getCart(Long userId);
    void updateCart(List<Long> newProductIds, Long userId);
    void clearCart(Long userId);
}
