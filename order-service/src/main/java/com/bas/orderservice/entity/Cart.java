package com.bas.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"orders"})
@Data
@SuperBuilder
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( unique = true, nullable = false, updatable = false)
    private Long Id;

    @Column(name = "user_id")
    private Long userId; // userId

    @JsonIgnore
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;
}
