package com.example.stemshop.services.favourites;

import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.FavouritesException;
import com.example.stemshop.models.Favourites;
import com.example.stemshop.models.Product;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.FavouritesRepository;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.repositories.UserRepository;
import com.example.stemshop.services.auth.AuthService;
import com.example.stemshop.services.user.UserService;
import com.example.stemshop.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "favouritesCache")
@RequiredArgsConstructor
public class FavouritesService {
    private final FavouritesRepository favouritesRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;
    private final UserService userService;

    private User getCurrentUser() {
        return userService.getUser();
    }

    @Cacheable(cacheNames = "favourites", key = "userId")
    @Transactional(readOnly = true)
    public List<ProductResponse> getFavourites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new FavouritesException("User not found"));
        final List<Product> products = favouritesRepository.findProductByUser(user)
                .orElse(new ArrayList<>());
        final List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            productResponses.add(productMapper.toResponse(product));
        }
        return productResponses;
    }

    @CachePut(cacheNames = "favourites", key = "userId")
    @Transactional
    public void updateFavourites(List<Long> newProductIds, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new FavouritesException("User not found"));

        List<Long> currentProductIds = favouritesRepository.findProductIdByUser(user)
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
            favouritesRepository.deleteByUserIdAndProductIdIn(user.getId(), toRemove);
        }

    }

    @CacheEvict(cacheNames = "favourites", key = "#userId")
    public void clearFavourites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new FavouritesException("User not found"));
        favouritesRepository.deleteAllByUserId(user.getId());
    }
}
