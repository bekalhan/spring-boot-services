package com.bas.orderservice.repository;

import com.bas.orderservice.entity.Order;
import com.bas.orderservice.response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    //Optional<Order>findByProductName(String productName);
    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    Optional<List<Order>>findByUserId(Long userId);
}