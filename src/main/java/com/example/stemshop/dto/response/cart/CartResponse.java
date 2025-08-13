package com.example.stemshop.dto.response.cart;

import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CartResponse {
    private Map<ProductResponse, Integer> products;
}
