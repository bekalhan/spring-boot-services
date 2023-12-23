package com.bas.shippingservice.repository;

import com.bas.shippingservice.entity.OrderItem;
import com.bas.shippingservice.entity.id.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository  extends JpaRepository<OrderItem, OrderItemId> {
    Optional<OrderItem> findByOrderIdAndProductId(Integer orderId,Integer productId);

}
