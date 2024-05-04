package com.bas.productservice.repository;

import com.bas.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);

    boolean existsByName(String title);

    @Query("SELECT p FROM Product p WHERE p.subCategoryId = :id")
    List<Product> findBySubCategoryId(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :id")
    List<Product> findByCategoryId(@Param("id") Long id);

}
