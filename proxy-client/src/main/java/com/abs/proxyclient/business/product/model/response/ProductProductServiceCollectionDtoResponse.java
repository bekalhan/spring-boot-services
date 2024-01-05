package com.abs.proxyclient.business.product.model.response;

import java.io.Serializable;
import java.util.Collection;

import com.abs.proxyclient.business.product.model.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductProductServiceCollectionDtoResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<ProductDto> collection;

}