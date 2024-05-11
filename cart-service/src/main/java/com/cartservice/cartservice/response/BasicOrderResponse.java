package com.cartservice.cartservice.response;

import com.cartservice.cartservice.entity.CartStatus;
import com.cartservice.cartservice.entity.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class BasicOrderResponse {
    private Long orderId;
    private Long userId;
    private Status status;
    private LocalDateTime createdAt;
}
