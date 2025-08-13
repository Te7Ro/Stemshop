package com.example.stemshop.services.favourites;

import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.FavouritesException;
import com.example.stemshop.models.Favourites;
import com.example.stemshop.models.Product;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.FavouritesRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.repositories.UserRepository;
import com.example.stemshop.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouritesService {
    private final FavouritesRepository favouritesRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Cacheable(value = "favourites", key = "#userId")
    @Transactional(readOnly = true)
    public List<ProductResponse> getFavourites(Long userId) {
        final List<Product> products = favouritesRepository.findProductByUserId(userId)
                .orElse(new ArrayList<>());
        final List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(productMapper.toResponse(product));
        }
        return productResponses;
    }

    @CachePut(value = "favourites", key = "#userId")
    @Transactional
    public void updateFavourites(Long userId, List<Long> newProductIds) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new FavouritesException("Пользователь не найден"));

        List<Long> currentProductIds = favouritesRepository.findProductIdByUserId(userId)
                .orElse(new ArrayList<>());

        List<Long> toAdd = newProductIds.stream()
                .filter(p -> !currentProductIds.contains(p))
                .toList();

        List<Long> toRemove = currentProductIds.stream()
                .filter(p -> !newProductIds.contains(p))
                .toList();

        toAdd.forEach(id -> {
            Favourites favourites = new Favourites();
            favourites.setProduct(productRepository.findById(id).orElseThrow());
            favourites.setUser(user);
            favouritesRepository.save(favourites);
        });

        if (!toRemove.isEmpty()) {
            favouritesRepository.deleteByUserIdAndProductIdIn(userId, toRemove);
        }

    }

    @CacheEvict(value = "favourites", key = "#userId")
    public void clearFavourites(Long userId) {
        favouritesRepository.deleteAllByUserId(userId);
    }
}
