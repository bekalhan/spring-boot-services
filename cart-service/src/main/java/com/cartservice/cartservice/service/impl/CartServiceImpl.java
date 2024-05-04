package com.cartservice.cartservice.service.impl;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.exception.CartNotExist;
import com.cartservice.cartservice.helper.CartResponseMapper;
import com.cartservice.cartservice.repository.CartRepository;
import com.cartservice.cartservice.request.CartRequest;
import com.cartservice.cartservice.response.BasicCartResponse;
import com.cartservice.cartservice.response.ProductResponse;
import com.cartservice.cartservice.response.UserResponse;
import com.cartservice.cartservice.response.CartResponse;
import com.cartservice.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepo;
    private final RestTemplate restTemplate;

    public List<BasicCartResponse> getAllCart() {
        List<Cart> carts=cartRepo.findAll();

        return carts.stream().map(cart-> CartResponseMapper.mapCartToCartResponseBasic(cart)).collect(Collectors.toList());
    }

    public CartResponse getCartById(Long cartId) {

        Cart cart=cartRepo.findById(cartId).orElseThrow(()-> new CartNotExist("Cart does not exist"));
        UserResponse userResponse
                = restTemplate.getForObject(
                "http://USER-SERVICE/user/" + cart.getUserId(),
                UserResponse.class
        );
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/action/product/" + cart.getProductId(),
                ProductResponse.class
        );
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .product(ProductResponse.builder()
                        .productId(productResponse.getProductId())
                        .name(productResponse.getName())
                        .price(productResponse.getPrice())
                        .imageUrl(productResponse.getImageUrl())
                        .color(productResponse.getColor())
                        .quantity(productResponse.getQuantity())
                        .brand(productResponse.getBrand())
                        .build())
                .user(UserResponse.builder()
                        .userId(userResponse.getUserId())
                        .firstName(userResponse.getFirstName())
                        .lastName(userResponse.getLastName())
                        .username(userResponse.getUsername())
                        .phNo(userResponse.getPhNo())
                        .build())
                .createdAt(cart.getCreatedAt())
                .quantity(cart.getQuantity())
                .build();
    }

    public Long addToCart(CartRequest cartRequest) {
        if(cartRequest.getQuantity()<=0)
            throw new IllegalArgumentException("Quantity should be greater than 0");
        Cart userAndProductExist = cartRepo.findUserAndProductExist(cartRequest.getUserId(), cartRequest.getProductId()).orElse(null);
        if(userAndProductExist==null){
            Cart cart=Cart.builder()
                    .userId(cartRequest.getUserId())
                    .productId(cartRequest.getProductId())
                    .quantity(cartRequest.getQuantity())
                    .totalPrice(cartRequest.getTotalPrice())
                    .createdAt(LocalDateTime.now())
                    .build();
            cartRepo.save(cart);
            return cart.getCartId();
        }
        else{
            userAndProductExist.setQuantity(userAndProductExist.getQuantity()+cartRequest.getQuantity());
            userAndProductExist.setTotalPrice(userAndProductExist.getTotalPrice()+cartRequest.getTotalPrice());
            cartRepo.save(userAndProductExist);
            return userAndProductExist.getCartId();
        }


    }

    public String deleteFromCart(Long cartId) {
        cartRepo.deleteById(cartId);
        return "Product deleted from cart";
    }


    public BasicCartResponse updateCart(Long cartId, CartRequest cartRequest) {
        Cart cart=cartRepo.findById(cartId).orElseThrow(() ->  new CartNotExist("Cart does not exist"));
        cart.setUserId(cartRequest.getUserId());
        cart.setProductId(cartRequest.getProductId());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setTotalPrice(cartRequest.getTotalPrice());
        cartRepo.save(cart);
        return CartResponseMapper.mapCartToCartResponseBasic(cart);
    }
    public List<Cart> viewCart(List<Long>cartIds) {
        return cartIds.stream().map(val->cartRepo.findById(val).get()).collect(Collectors.toList());
    }

    public List<CartResponse> getCartsByUserId(Long userId) {
        List<Cart> carts=cartRepo.findCartsByUserId(userId).orElseThrow(()-> new CartNotExist("Cart does not exist"));
        List<CartResponse> cartResponses = new ArrayList<>();


        for (Cart cart : carts) {
            UserResponse userResponse = restTemplate.getForObject(
                    "http://USER-SERVICE/user/" + cart.getUserId(),
                    UserResponse.class
            );
            ProductResponse productResponse
                    = restTemplate.getForObject(
                    "http://PRODUCT-SERVICE/product/action/product/" + cart.getProductId(),
                    ProductResponse.class
            );
            CartResponse cartResponse = CartResponseMapper.mapCartToCartResponse(cart);
            cartResponse.setUser(userResponse);
            cartResponse.setProduct(productResponse);

            cartResponses.add(cartResponse);
        }

        return cartResponses;
    }

}
