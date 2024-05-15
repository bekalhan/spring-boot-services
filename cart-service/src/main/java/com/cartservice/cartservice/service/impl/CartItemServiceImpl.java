package com.cartservice.cartservice.service.impl;

import com.cartservice.cartservice.entity.CartItem;
import com.cartservice.cartservice.exception.CartItemNotExist;
import com.cartservice.cartservice.helper.CartItemResponseMapper;
import com.cartservice.cartservice.repository.CartItemRepository;
import com.cartservice.cartservice.repository.CartRepository;
import com.cartservice.cartservice.request.CartItemRequest;
import com.cartservice.cartservice.response.BasicCartItemResponse;
import com.cartservice.cartservice.response.ProductResponse;
import com.cartservice.cartservice.response.UserResponse;
import com.cartservice.cartservice.response.CartItemResponse;
import com.cartservice.cartservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepo;
    private final CartRepository cartRepo;
    private final RestTemplate restTemplate;

    public List<BasicCartItemResponse> getAllCart() {
        List<CartItem> cartItems =cartItemRepo.findAll();

        return cartItems.stream().map(cart-> CartItemResponseMapper.mapCartItemToCartItemResponseBasic(cart)).collect(Collectors.toList());
    }

    public CartItemResponse getCartById(Long cartItemId) {

        CartItem cartItem =cartItemRepo.findById(cartItemId).orElseThrow(()-> new CartItemNotExist("Cart does not exist"));
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/action/product/" + cartItem.getProductId(),
                ProductResponse.class
        );
        CartItemResponse cartItemResponse = CartItemResponseMapper.mapCartItemToCartItemResponse(cartItem);
        cartItemResponse.setProduct(CartItemResponseMapper.buildProduct(productResponse));
        return cartItemResponse;
    }

    public Long addToCart(CartItemRequest cartItemRequest) {
        if(cartItemRequest.getQuantity()<=0)
            throw new IllegalArgumentException("Quantity should be greater than 0");
        CartItem isProductExist = cartItemRepo.findProductExist(cartItemRequest.getProductId()).orElse(null);
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/action/product/" + cartItemRequest.getProductId(),
                ProductResponse.class
        );
        if(isProductExist==null){

            CartItem cartItem = CartItemResponseMapper.mapCartItemRequestToCart(cartItemRequest);
            cartItem.setCart(cartRepo.findById(cartItemRequest.getCartId()).get());
            cartItem.setTotalPrice(cartItemRequest.getQuantity()* productResponse.getPrice());
            cartItemRepo.save(cartItem);
            return cartItem.getCartItemId();
        }
        else{
            isProductExist.setQuantity(isProductExist.getQuantity()+ cartItemRequest.getQuantity());
            isProductExist.setTotalPrice(isProductExist.getTotalPrice()+ (cartItemRequest.getQuantity()* productResponse.getPrice()));
            cartItemRepo.save(isProductExist);
            return isProductExist.getCartItemId();
        }
    }
    public String deleteFromCart(Long cartItemId) {
        cartItemRepo.deleteById(cartItemId);
        return "Product deleted from cartItem";
    }


    public BasicCartItemResponse updateCart(Long cartItemId, CartItemRequest cartItemRequest) {
        CartItem cartItem =cartItemRepo.findById(cartItemId).orElseThrow(() ->  new CartItemNotExist("Cart does not exist"));
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/action/product/" + cartItemRequest.getProductId(),
                ProductResponse.class
        );
        cartItem.setProductId(cartItemRequest.getProductId());
        cartItem.setCart(cartRepo.findById(cartItemRequest.getCartId()).get());
        cartItem.setQuantity(cartItemRequest.getQuantity());
        cartItem.setStatus(cartItemRequest.getStatus());
        cartItem.setTotalPrice(cartItemRequest.getQuantity()* productResponse.getPrice());
        cartItemRepo.save(cartItem);
        return CartItemResponseMapper.mapCartItemToCartItemResponseBasic(cartItem);
    }
    public List<CartItem> viewCart(List<Long>cartItemId) {
        return cartItemId.stream().map(val->cartItemRepo.findById(val).get()).collect(Collectors.toList());
    }

    public List<CartItemResponse> getCartsByCartId(Long cartId) {
        List<CartItem> cartItems =cartItemRepo.findCartItemsByCartId(cartId).orElseThrow(()-> new CartItemNotExist("Cart does not exist"));
        List<CartItemResponse> cartItemRespons = new ArrayList<>();

        for (CartItem cartItem : cartItems) {

            ProductResponse productResponse
                    = restTemplate.getForObject(
                    "http://PRODUCT-SERVICE/product/action/product/" + cartItem.getProductId(),
                    ProductResponse.class
            );
            CartItemResponse cartItemResponse = CartItemResponseMapper.mapCartItemToCartItemResponse(cartItem);
            cartItemResponse.setProduct(productResponse);
            cartItemRespons.add(cartItemResponse);
        }
        return cartItemRespons;
    }

}
