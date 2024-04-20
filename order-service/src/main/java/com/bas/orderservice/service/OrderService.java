package com.bas.orderservice.service;


import com.bas.orderservice.dto.OrderDTO;
import com.bas.orderservice.entity.Order;

import java.util.List;


public interface OrderService {
   Long bookOrder(OrderDTO orderDto);

   String cancelOrder(Long orderId);

   List<Order> viewOrders(List<Long> orderIds);

    List<Order> viewOrderByStatus(List<Long> orderIds, String status);

    List<OrderDTO> findAll();
    OrderDTO findById(final Long orderId);
    OrderDTO save(final OrderDTO orderDto);
    OrderDTO update(final OrderDTO orderDto);
    OrderDTO update(final Long orderId, final OrderDTO orderDto);
    void deleteById(final Long orderId);
}
