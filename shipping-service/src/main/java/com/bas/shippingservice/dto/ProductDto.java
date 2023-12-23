package com.bas.shippingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String title;
    private String imageUrl;
    private String sku;
    private Double price;
    private Integer quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OrderItemDto> orderItemDtos;
}
