package com.example.stemshop.util;

import com.example.stemshop.dto.response.order.OrderItemForResponse;
import com.example.stemshop.dto.response.order.OrderResponse;
import com.example.stemshop.models.Order;
import com.example.stemshop.models.OrderItem;
import com.example.stemshop.repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderItemRepository orderItemRepository;

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setUsername(order.getUser().getFullName());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());

        List<OrderItemForResponse> itemsForResponse = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder(order);
        for (OrderItem orderItem : orderItems) {
            OrderItemForResponse itemForResponse = new OrderItemForResponse();
            itemForResponse.setProductId(orderItem.getProduct().getId());
            itemForResponse.setName(orderItem.getProduct().getName());
            itemForResponse.setPrice(orderItem.getProduct().getPrice());
            itemForResponse.setQuantity(orderItem.getQuantity());
            itemsForResponse.add(itemForResponse);
        }
        response.setItems(itemsForResponse);

        return response;
    }
}
