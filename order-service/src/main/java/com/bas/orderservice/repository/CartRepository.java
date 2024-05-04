package com.bas.orderservice.repository;

import com.bas.orderservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>{
    //kaç tane ürün olduğunu döndüren method
//    @Query("""
//
//""")
//    Integer countProduct(Long id);
//
}
