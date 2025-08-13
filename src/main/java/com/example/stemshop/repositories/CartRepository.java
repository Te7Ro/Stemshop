package com.example.stemshop.repositories;

import com.example.stemshop.models.Cart;
import com.example.stemshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<List<Cart>> findAllByUser(User user);

    @Query("SELECT c.product.id FROM Cart c WHERE c.user.id = :userId")
    List<Long> findProductIdsByUserId(@Param("userId") Long userId);

    void deleteByUserIdAndProductIdIn(Long userId, List<Long> productIds);

    void deleteAllByUserId(Long userId);
}
