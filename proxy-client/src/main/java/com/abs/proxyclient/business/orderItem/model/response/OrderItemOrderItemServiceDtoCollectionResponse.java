package com.abs.proxyclient.business.orderItem.model.response;

import java.io.Serializable;
import java.util.Collection;

import com.abs.proxyclient.business.orderItem.model.OrderItemDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemOrderItemServiceDtoCollectionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<OrderItemDto> collection;

}