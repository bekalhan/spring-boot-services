package com.bas.productservice.helper;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.entity.Category;
import com.bas.productservice.entity.Product;
import com.bas.productservice.response.ProductResponse;
import org.hibernate.query.Page;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapperHelper {
    public static ProductDTO map(final Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categoryDto(
                        CategoryDto.builder()
                                .categoryId(product.getCategory().getCategoryId())
                                .title(product.getCategory().getTitle())
                                .imageUrl(product.getCategory().getImageUrl())
                                .build())
                .description(product.getDescription())
                .build();
    }

    public static Product map(final ProductDTO productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .imageUrl(productDto.getImageUrl())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .description(productDto.getDescription())
                //.category(category)//? is it good approach, test it.!
                .category(
                        Category.builder()
                                .categoryId(productDto.getCategoryDto().getCategoryId())
                                .title(productDto.getCategoryDto().getTitle())
                                .imageUrl(productDto.getCategoryDto().getImageUrl())
                                .build())
                .build();
    }

//    public static ProductResponse mapProductResponse(List<ProductDTO> content, Page<Product> products){
//        ProductResponse productResponse = new ProductResponse();
//        productResponse.setContent(content);
//        productResponse.setPageNo(products.getNumber());
//        productResponse.setPageSize(products.getSize());
//        productResponse.setTotalElements(products.getTotalElements());
//        productResponse.setTotalPages(products.getTotalPages());
//        productResponse.setLast(products.isLast());
//        return productResponse;
//    }

    public static Set<ProductDTO> mapList(Set<Product> productList) {
        return productList.stream()
                .map(ProductMapperHelper::map) // Bu, her bir Product nesnesini ProductDto'ya dönüştürür
                .collect(Collectors.toSet());
    }
}
