package com.example.stemshop.services.cart;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.models.Cart;

import java.util.List;

public interface CartService {
    CartResponse getCart();
    void updateCart(List<Long> newProductIds);
    void clearCart();
}
