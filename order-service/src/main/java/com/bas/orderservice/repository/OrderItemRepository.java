package com.bas.orderservice.repository;

import com.bas.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderItem")
    Optional<List<OrderItem>> findOrderItemsByOrderId(Long orderItem);
}
