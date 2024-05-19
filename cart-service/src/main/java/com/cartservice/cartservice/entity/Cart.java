package com.cartservice.cartservice.entity;

import com.cartservice.cartservice.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "_cart")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
    @CreatedDate
    private LocalDateTime createdAt;
    @JoinColumn(name = "user_id")
    private Long userId;
    private Status status;
    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

}
