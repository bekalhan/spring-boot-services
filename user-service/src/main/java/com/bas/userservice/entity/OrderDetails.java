package com.bas.userservice.entity;

import com.bas.userservice.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    private Long orderId;
    private String productName;
    private Status status;
    private Long price;
    private String description;
}
