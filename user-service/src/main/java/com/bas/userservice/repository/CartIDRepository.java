package com.bas.userservice.repository;

import com.bas.userservice.entity.CartIDS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartIDRepository extends JpaRepository<CartIDS,Long> {
}
