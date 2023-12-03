package com.bas.productservice.repository;

import com.bas.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByTitle(String title);

}
