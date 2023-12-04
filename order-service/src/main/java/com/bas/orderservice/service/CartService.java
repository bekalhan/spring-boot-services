package com.bas.orderservice.service;

import com.bas.orderservice.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto> findAll();
    CartDto findById(final Integer cartId);
    CartDto save(final CartDto cartDto);
    CartDto update(final CartDto cartDto);
    CartDto update(final Integer cartId, final CartDto cartDto);
    void deleteById(final Integer cartId);
}
