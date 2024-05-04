package com.cartservice.cartservice.repository;

import com.cartservice.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c WHERE c.userId = :userID")
    Optional<List<Cart>> findCartsByUserId(Long userID);

    @Query("SELECT c FROM Cart c WHERE c.userId = :userId AND c.productId = :productId")
    Optional<Cart> findUserAndProductExist(Long userId,Long productId);


    //Optional<Cart>findByProductName(String name);
}
