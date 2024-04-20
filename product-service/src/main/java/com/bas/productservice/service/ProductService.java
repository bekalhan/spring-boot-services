package com.bas.productservice.service;

import com.bas.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> showAllProducts();

    ProductDTO findProductByName(String name);

    ProductDTO findProductId(Long productId);
    ProductDTO  addProduct(ProductDTO productDTO);

    String deleteProduct(Long productId);
    ProductDTO patchProduct(Long productId, ProductDTO productDto);
}
