package com.bas.productservice.helper;

import com.bas.productservice.dto.ProductDto;
import com.bas.productservice.entity.Category;
import com.bas.productservice.entity.Product;
import com.bas.productservice.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ProductMapperHelper {
    public static ProductDto map(final Product product) {
        return ProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .imageUrl(product.getImageUrl())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryId(product.getCategory().getCategoryId())
                .build();
    }

    public static Product map(final ProductDto productDto,Category category) {
        return Product.builder()
                .productId(productDto.getProductId())
                .title(productDto.getTitle())
                .imageUrl(productDto.getImageUrl())
                .sku(productDto.getSku())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(category)
                .build();
    }

    public static ProductResponse mapProductResponse(List<ProductDto> content, Page<Product> products){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());
        return productResponse;
    }

    public static Set<ProductDto> mapList(Set<Product> productList) {
        return productList.stream()
                .map(ProductMapperHelper::map) // Bu, her bir Product nesnesini ProductDto'ya dönüştürür
                .collect(Collectors.toSet());
    }
}
