package com.bas.orderservice.repository;

import com.bas.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    //Optional<Order>findByProductName(String productName);
}