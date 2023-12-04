package com.bas.orderservice.service;

import com.bas.orderservice.dto.OrderDto;
import com.bas.orderservice.dto.OrderResponse;

import java.util.List;


public interface OrderService {
    List<OrderDto> findAll();
    OrderResponse getAllProductsWithPageableAndSorting(int pageNo, int pageSize, String sortBy, String sortDir);

    OrderDto findById(final Integer orderId);
    OrderDto save(final OrderDto orderDto);
    OrderDto update(final OrderDto orderDto);
    OrderDto update(final Integer orderId, final OrderDto orderDto);
    void deleteById(final Integer orderId);

}
