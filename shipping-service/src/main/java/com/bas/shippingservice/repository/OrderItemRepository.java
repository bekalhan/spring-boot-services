package com.bas.shippingservice.repository;

import com.bas.shippingservice.entity.OrderItem;
import com.bas.shippingservice.entity.id.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem, OrderItemId> {
}
