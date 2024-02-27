package com.bas.productservice.service.impl;

import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.entity.Product;
import com.bas.productservice.exception.ProductNotFound;
import com.bas.productservice.service.ProductService;
import com.bas.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
   private final ProductRepository productRepository;
    public List<ProductDTO> showAllProducts(){
        return productRepository.findAll().stream().map((product)-> ProductDTO.
                builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build()).collect(Collectors.toList());
    }

    public ProductDTO findProductByName(String name) throws ProductNotFound {
        Optional<Product> product=productRepository.findByName(name);
        if(product.isEmpty())throw new ProductNotFound("Product is not there");
        return ProductDTO.builder()
                .name(product.get().getName())
                .price(product.get().getPrice())
                .description(product.get().getDescription())
                .build();
    }

    public String addProduct(ProductDTO productDTO){
        productRepository.save(Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .build());
        return "Product added successfully";
    }

    public String deleteProduct(String name) throws ProductNotFound {
        Optional<Product> product=productRepository.findByName(name);
        if (product.isEmpty())throw new ProductNotFound("Product is not there");
        else{
            productRepository.delete(product.get());
        }
        return "Product deleted successfully";
    }

}
