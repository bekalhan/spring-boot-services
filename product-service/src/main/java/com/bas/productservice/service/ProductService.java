package com.bas.productservice.service;

import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.dto.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDTO> showAllProducts();

    List<ProductDTO> showAllProductsBySubCategoryId(final Long id);

    List<ProductDTO> showAllProductsByCategoryId(final Long id);

    ProductDTO findProductByName(String name);

    ProductDTO findProductId(Long productId);
    ProductDTO  addProduct(ProductRequest productDTO);

    String deleteProduct(Long productId);
    ProductDTO patchProduct(Long productId, ProductDTO productDto);
}
