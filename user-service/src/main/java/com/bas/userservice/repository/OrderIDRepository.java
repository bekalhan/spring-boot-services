package com.bas.userservice.repository;

import com.bas.userservice.entity.OrderIDS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderIDRepository extends JpaRepository<OrderIDS,Long> {
}
