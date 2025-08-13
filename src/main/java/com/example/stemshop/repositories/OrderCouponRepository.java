package com.example.stemshop.repositories;

import com.example.stemshop.models.OrderCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCouponRepository extends JpaRepository<OrderCoupon, Long> {
}
