package com.bas.userservice.feign;

import com.bas.userservice.dto.Cart;
import com.bas.userservice.dto.CartDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("CART-SERVICE")
public interface CartFeign {

    @PostMapping("cart/action/addProduct")
    ResponseEntity<Long> addToCart(@RequestBody @Valid CartDTO cartDTO);

    @DeleteMapping("cart/action/deleteProduct")
    ResponseEntity<String>deleteFromCart(@RequestParam Long cartId);

    @PostMapping("cart/action/allProducts")
    ResponseEntity<List<Cart>>viewCart(List<Long> cartIds);
}
