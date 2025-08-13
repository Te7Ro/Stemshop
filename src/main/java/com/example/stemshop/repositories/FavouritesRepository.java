package com.example.stemshop.repositories;

import com.example.stemshop.models.Favourites;
import com.example.stemshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Optional<List<Product>> findProductByUserId(Long id);
    Optional<List<Long>> findProductIdByUserId(Long id);
    void deleteByUserIdAndProductIdIn(Long id, List<Long> productIds);
    void deleteAllByUserId(Long id);
}
