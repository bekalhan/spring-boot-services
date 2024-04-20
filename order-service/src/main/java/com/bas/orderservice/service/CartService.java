package com.bas.orderservice.service;

import com.bas.orderservice.dto.CartDto;

import java.util.List;

public interface CartService{
    List<CartDto> findAll();
    CartDto findById(Long id);
    CartDto save(CartDto cartDto);
    CartDto update(CartDto cartDto);
    CartDto update(Long id, CartDto cartDto);
    void deleteById(Long id);

}
