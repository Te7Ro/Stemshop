package com.example.stemshop.repositories;

import com.example.stemshop.models.Order;
import com.example.stemshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    @Query(value = """
    SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
    FROM orders o
    JOIN order_items oi ON o.id = oi.order_id
    WHERE o.user_id = :userId
      AND oi.product_id = :productId
""", nativeQuery = true)
    boolean existsProductByUser(@Param("userId") Long userId,
                                @Param("productId") Long productId);
}
