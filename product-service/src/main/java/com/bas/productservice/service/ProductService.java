package com.bas.productservice.service;

import com.bas.productservice.dto.ProductDto;
import com.bas.productservice.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse getAllProductsWithPageableAndSorting(int pageNo, int pageSize, String sortBy, String sortDir);
    List<ProductDto> getAllProducts();

    ProductDto getProductById(final Long productId);
    ProductDto createProduct(final ProductDto productDto);
    ProductDto patchProduct(Long productId, ProductDto productDto);

    void deleteProduct(final Long productId);
}
