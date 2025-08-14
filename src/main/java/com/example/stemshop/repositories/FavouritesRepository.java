package com.example.stemshop.repositories;

import com.example.stemshop.models.Favourites;
import com.example.stemshop.models.Product;
import com.example.stemshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Optional<List<Product>> findProductByUser(User user);
    Optional<List<Long>> findProductIdByUser(User user);
    void deleteByUserIdAndProductIdIn(Long id, List<Long> productIds);
    void deleteAllByUserId(Long id);
}
