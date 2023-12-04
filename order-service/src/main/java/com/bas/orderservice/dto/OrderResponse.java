package com.bas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private List<OrderDto> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private boolean last;
    private long totalElements;
}