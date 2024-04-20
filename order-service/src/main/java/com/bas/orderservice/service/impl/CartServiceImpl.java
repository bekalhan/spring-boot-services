package com.bas.orderservice.service.impl;

import com.bas.orderservice.constant.AppConstant;
import com.bas.orderservice.dto.CartDto;
import com.bas.orderservice.dto.UserDto;
import com.bas.orderservice.helper.CartMappingHelper;
import com.bas.orderservice.repository.CartRepository;
import com.bas.orderservice.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<CartDto> findAll() {

        return this.cartRepository.findAll().stream()
                .map(cart -> {
                    CartDto cartDto = CartDto.builder()
                            .cartId(cart.getCartId())
                            .userId(cart.getUserId())
                            .build();
                    return cartDto;
                })
                .map(c-> {c.setUserDto(this.restTemplate.getForObject(
                        AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL+"/"+c.getUserDto().getUserId(), UserDto.class));
                    return c;})
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public CartDto findById(Long cartId) {
        return this.cartRepository.findById(cartId)
                .map(cart -> {
                    CartDto cartDto = CartDto.builder()
                            .cartId(cart.getCartId())
                            .userId(cart.getUserId())
                            .build();
                    return cartDto;
                })
                .map(c-> {c.setUserDto(this.restTemplate.getForObject(
                        AppConstant.DiscoveredDomainsApi.USER_SERVICE_API_URL+"/"+c.getUserDto().getUserId(), UserDto.class));
                    return c;})
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public CartDto save(CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository.save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository.save(CartMappingHelper.map(cartDto)));
    }

    @Override
    public CartDto update(Long cartId, CartDto cartDto) {
        return CartMappingHelper.map(this.cartRepository
                .save(CartMappingHelper.map(this.findById(cartId))));
    }

    @Override
    public void deleteById(Long cartId) {
        this.cartRepository.deleteById(cartId);
    }
}
