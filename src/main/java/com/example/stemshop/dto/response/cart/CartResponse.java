package com.example.stemshop.dto.response.cart;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    private List<CartItem> items;
    private int totalQuantity;
    private int totalAmount;
}
