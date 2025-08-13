package com.example.stemshop.dto.response.order;

import com.example.stemshop.data.enums.OrderStatus;
import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.dto.response.product.ProductResponse;
import lombok.Data;

import java.util.Map;

@Data
public class OrderResponse {
    private int totalPrice;
    private OrderStatus status;
    private Map<ProductResponse, Integer> products;
}
