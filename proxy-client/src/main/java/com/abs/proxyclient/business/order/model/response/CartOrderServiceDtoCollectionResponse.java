package com.abs.proxyclient.business.order.model.response;

import com.abs.proxyclient.business.order.model.CartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartOrderServiceDtoCollectionResponse {
    private Collection<CartDto> collection;
}
