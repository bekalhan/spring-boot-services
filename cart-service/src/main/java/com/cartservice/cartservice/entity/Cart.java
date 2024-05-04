package com.cartservice.cartservice.entity;

import com.cartservice.cartservice.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "cart")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @JoinColumn(name="product_id")
    private Long productId;
    @JoinColumn(name="user_id")
    private Long userId;
    private int quantity;
    private Double totalPrice;

    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    @CreatedDate
    private LocalDateTime createdAt;
}