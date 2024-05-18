package com.bas.paymentservice.repository;

import com.bas.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
    @Query("SELECT p FROM Payment p WHERE p.userId = :userId")
    Optional<Payment> findPaymentByUserId(Long userId);
}
