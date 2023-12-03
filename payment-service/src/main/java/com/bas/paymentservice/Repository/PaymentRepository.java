package com.bas.paymentservice.Repository;

import com.bas.paymentservice.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment,Integer>{
}
