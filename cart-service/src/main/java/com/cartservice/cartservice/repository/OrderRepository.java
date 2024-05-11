package com.cartservice.cartservice.repository;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT c FROM Cart c WHERE c.userId = :userID")
    Optional<List<Cart>> findCartsByUserId(Long userID);

}
