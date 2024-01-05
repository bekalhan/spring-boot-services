package com.abs.proxyclient.business.order.model.response;

import com.abs.proxyclient.business.order.model.OrderDto;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderOrderServiceDtoCollectionResponse {
    private Collection<OrderDto> collection;

}
