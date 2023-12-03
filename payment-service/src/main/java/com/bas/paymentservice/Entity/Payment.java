package com.bas.paymentservice.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Payment extends AbstractMappedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", unique = true, nullable = false, updatable = false)
    private Integer paymentId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

}
