package com.example.stemshop.services.cart;

import com.example.stemshop.dto.response.cart.CartResponse;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.CartException;
import com.example.stemshop.models.Cart;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.CartRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.repositories.UserRepository;
import com.example.stemshop.services.user.UserService;
import com.example.stemshop.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
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
@CacheConfig(cacheNames = "cartCache")
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Override
    @Cacheable(cacheNames = "cart", key = "#userId")
    @Transactional(readOnly = true)
    public CartResponse getCart(Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new CartException("User not found"));
        final List<Cart> cart = cartRepository.findAllByUser(user).orElse(new ArrayList<>());
        Map<ProductResponse, Integer> response = new HashMap<>();
        for(Cart position : cart) {
            response.put(productMapper.toResponse(position.getProduct()), position.getQuantity());
        }
        return new CartResponse(response);
    }

    @Override
    @CachePut(cacheNames = "cart", key = "#root.target.getCurrentUser()")
    @Transactional
    public void updateCart(List<Long> newProductIds, Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new CartException("User not found"));

        List<Long> currentProductIds = cartRepository.findProductIdsByUserId(user.getId());

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
            cartRepository.save(cart);
        });

        if (!toRemove.isEmpty()) {
            cartRepository.deleteByUserIdAndProductIdIn(user.getId(), toRemove);
        }
    }

    @Override
    @CacheEvict(cacheNames = "cart", key = "#root.target.getCurrentUser()")
    public void clearCart(Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new CartException("User not found"));
        cartRepository.deleteAllByUser(user);
    }
}
