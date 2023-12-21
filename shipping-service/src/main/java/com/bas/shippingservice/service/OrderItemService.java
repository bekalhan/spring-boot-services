package com.bas.shippingservice.service;

import com.bas.shippingservice.dto.OrderItemDto;
import com.bas.shippingservice.entity.id.OrderItemId;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAll();
    OrderItemDto findById(final OrderItemId orderItemId);
    OrderItemDto save(final OrderItemDto orderItemDto);
    OrderItemDto update(final OrderItemDto orderItemDto);
    void deleteById(final OrderItemId orderItemId);
}
