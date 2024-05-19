package com.bas.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long productId;
    private String name;
    private Double price;
    private String imageUrl;
    private Integer quantity;
    private String brand;
    private String color;
}
