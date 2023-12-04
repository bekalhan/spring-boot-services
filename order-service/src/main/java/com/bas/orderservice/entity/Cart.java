package com.bas.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id",unique = true,nullable = false,updatable = false)
    Integer cartId;

    @Column(name = "user_id")
    Integer userId;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    Set<Order> orders;

}
