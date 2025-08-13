package com.example.stemshop.services.cart;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.CartException;
import com.example.stemshop.models.Cart;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.CartRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.repositories.UserRepository;
import com.example.stemshop.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Cacheable(value = "cart", key = "#userId")
    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        final User user = userRepository.findById(userId).orElse(new User());
        final List<Cart> cart = cartRepository.findAllByUser(user).orElse(new ArrayList<>());
        Map<ProductResponse, Integer> response = new HashMap<>();
        for(Cart position : cart) {
            response.put(productMapper.toResponse(position.getProduct()), position.getQuantity());
        }
        return new CartResponse(response);
    }

    @Override
    @CachePut(value = "cart", key = "#userId")
    @Transactional
    public void updateCart(Long userId, List<Long> newProductIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CartException("Пользователь не зарегистрирован"));

        List<Long> currentProductIds = cartRepository.findProductIdsByUserId(userId);

        List<Long> toAdd = newProductIds.stream()
                .filter(p -> !currentProductIds.contains(p))
                .toList();

        List<Long> toRemove = currentProductIds.stream()
                .filter(p -> !newProductIds.contains(p))
                .toList();

        toAdd.forEach(id -> {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(productRepository.findById(id).orElseThrow(() -> new CartException("Товар не найден")));
            cart.setQuantity(cart.getQuantity()+1);
        });

        if (!toRemove.isEmpty()) {
            cartRepository.deleteByUserIdAndProductIdIn(userId, toRemove);
        }
    }

    @Override
    @CacheEvict(value = "cart", key = "#userId")
    public void clearCart(Long userId) {
        cartRepository.deleteAllByUserId(userId);
    }
}
