package com.cartservice.cartservice.repository;

import com.cartservice.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query("SELECT c FROM CartItem c WHERE c.cart.cartId = :cartId")
    Optional<List<CartItem>> findCartItemsByCartId(Long cartId);

    @Query("SELECT c FROM CartItem c WHERE c.productId = :productId")
    Optional<CartItem> findProductExist(Long productId);


    //Optional<Cart>findByProductName(String name);
}
