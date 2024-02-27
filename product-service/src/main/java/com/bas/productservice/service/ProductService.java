package com.bas.productservice.service;

import com.bas.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> showAllProducts();

    ProductDTO findProductByName(String name);

    String addProduct(ProductDTO productDTO);

    String deleteProduct(String name);
}
