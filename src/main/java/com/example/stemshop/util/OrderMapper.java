package com.example.stemshop.util;

import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.models.Order;
import com.example.stemshop.models.OrderItem;
import com.example.stemshop.repositories.OrderItemRepository;
import com.example.stemshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemRepository orderItemRepository;
    private final ProductMapper productMapper;

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());

        Map<ProductResponse, Integer> cartMap = new HashMap<>();
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        for (OrderItem orderItem : orderItems) {
            cartMap.put(productMapper.toResponse(orderItem.getProduct()), orderItem.getQuantity());
        }
        response.setProducts(cartMap);

        return response;
    }
}
