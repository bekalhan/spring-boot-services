package com.bas.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long cartId;
    private Long userId;

    @JsonProperty("order")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OrderDTO> orderDtos;

    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto userDto;
}
