package com.cartservice.cartservice.repository;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{

//    @Query("SELECT c FROM CartItem c WHERE c.userId = :userID")
//    Optional<List<CartItem>> findCartsByUserId(Long userID);
    Optional<Cart> findByUserId(Long userId);
}
