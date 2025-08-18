package com.example.stemshop.services.cart;

import com.example.stemshop.dto.response.cart.CartItem;
import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOps;
    private final ProductRepository productRepository;

    @Value("${app.cart.ttl-seconds:604800}")
    private int cartTtlSeconds;

    private String cartKey(Long userId) {
        return "cart:" + userId;
    }

    public void addItem(Long userId, Long productId, int qty) {
        String key = cartKey(userId);
        Integer existingQty = Optional.ofNullable(hashOps.get(key, productId.toString()))
                .map(Integer::parseInt)
                .orElse(0);
        hashOps.put(key, productId.toString(), String.valueOf(existingQty + qty));
        redisTemplate.expire(key, Duration.ofSeconds(cartTtlSeconds));
    }

    public void setQuantity(Long userId, Long productId, int qty) {
        String key = cartKey(userId);
        if (qty <= 0) {
            hashOps.delete(key, productId.toString());
        } else {
            hashOps.put(key, productId.toString(), String.valueOf(qty));
        }
        redisTemplate.expire(key, Duration.ofSeconds(cartTtlSeconds));
    }

    public void removeItem(Long userId, Long productId) {
        hashOps.delete(cartKey(userId), productId.toString());
    }

    public void clear(Long userId) {
        redisTemplate.delete(cartKey(userId));
    }

    public CartResponse getCart(Long userId) {
        String key = cartKey(userId);
        Map<String, String> map = hashOps.entries(key);

        List<CartItem> items = new ArrayList<>();
        int totalQty = 0;
        int totalAmount = 0;

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Long productId = Long.valueOf(entry.getKey());
            int qty = Integer.parseInt(entry.getValue());

            productRepository.findById(productId).ifPresent(product -> {
                CartItem item = CartItem.builder()
                        .productId(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity(qty)
                        .build();
                items.add(item);
            });
        }

        for (CartItem item : items) {
            totalQty += item.getQuantity();
            totalAmount += item.getPrice() * item.getQuantity();
        }

        redisTemplate.expire(key, Duration.ofSeconds(cartTtlSeconds));

        return CartResponse.builder()
                .items(items)
                .totalQuantity(totalQty)
                .totalAmount(totalAmount)
                .build();
    }
}
