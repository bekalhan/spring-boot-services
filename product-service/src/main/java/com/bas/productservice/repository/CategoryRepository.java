package com.bas.productservice.repository;

import com.bas.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);
}
