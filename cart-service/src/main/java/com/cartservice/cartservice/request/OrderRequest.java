package com.cartservice.cartservice.request;

import com.cartservice.cartservice.entity.Cart;
import com.cartservice.cartservice.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private Long userId;
    private Status status;
    private Double orderPrice;
    private Integer totalQuantity;
}
