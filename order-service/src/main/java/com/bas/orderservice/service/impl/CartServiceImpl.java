package com.bas.orderservice.service.impl;

import com.bas.orderservice.constant.AppConstant;
import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.UserDto;
import com.bas.orderservice.exception.CartNotFoundException;
import com.bas.orderservice.helper.CartMappingHelper;
import com.bas.orderservice.repository.CartRepository;
import com.bas.orderservice.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    @Autowired
    private final RestTemplate restTemplate;

    @Override
    public List<CartDto> findAll() {
        return this.cartRepository.findAll()
                .stream()
                .map(CartMappingHelper::map)
                .map(c -> {
                    c.setUserDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .USER_SERVICE_API_URL + "/" + c.getUserDto().getUserId(), UserDto.class));
                    return c;
                })
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CartDto findById(Integer cartId) {
        return this.cartRepository.findById(cartId)
                .map(CartMappingHelper::map)
                .map(c -> {
                    c.setUserDto(this.restTemplate.getForObject(AppConstant.DiscoveredDomainsApi
                            .USER_SERVICE_API_URL + "/" + c.getUserDto().getUserId(), UserDto.class));
                    return c;
                })
                .orElseThrow(() -> new CartNotFoundException(String
                        .format("Cart with id: %d not found", cartId)));
    }

    @Override
    public CartDto save(CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository
                .save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository
                .save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(Integer cartId, CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository
                .save(CartMappingHelper.map(this.findById(cartId))));
    }

    @Override
    public void deleteById(Integer cartId) {
        this.cartRepository.deleteById(cartId);
    }
}
