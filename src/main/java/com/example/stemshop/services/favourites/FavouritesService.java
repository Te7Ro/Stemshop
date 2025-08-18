package com.example.stemshop.services.favourites;

import com.example.stemshop.dto.response.favourites.FavouritesItem;
import com.example.stemshop.dto.response.favourites.FavouritesResponse;
import com.example.stemshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavouritesService {
    private final RedisTemplate<String, String> redisTemplate;
    private final SetOperations<String, String> setOps;
    private final ProductRepository productRepository;

    @Value("${app.favorites.ttl-seconds:2592000}") // 30 дней по умолчанию
    private int favoritesTtlSeconds;

    private String favoritesKey(Long userId) {
        return "favorites:" + userId;
    }
    public void addItem(Long userId, Long productId) {
        String key = favoritesKey(userId);
        setOps.add(key, productId.toString());
        redisTemplate.expire(key, Duration.ofSeconds(favoritesTtlSeconds));
    }

    public void removeItem(Long userId, Long productId) {
        setOps.remove(favoritesKey(userId), productId.toString());
    }

    public void clear(Long userId) {
        redisTemplate.delete(favoritesKey(userId));
    }

    public FavouritesResponse getFavorites(Long userId) {
        String key = favoritesKey(userId);
        Set<String> ids = setOps.members(key);

        List<FavouritesItem> items = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                Long productId = Long.valueOf(id);
                productRepository.findById(productId).ifPresent(product -> {
                    FavouritesItem item = FavouritesItem.builder()
                            .productId(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .build();
                    items.add(item);
                });
            }
        }

        redisTemplate.expire(key, Duration.ofSeconds(favoritesTtlSeconds));

        return FavouritesResponse.builder()
                .items(items)
                .build();
    }
}
