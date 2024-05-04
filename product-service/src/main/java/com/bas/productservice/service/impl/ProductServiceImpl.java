package com.bas.productservice.service.impl;

import com.bas.productservice.dto.CategoryDto;
import com.bas.productservice.dto.ProductDTO;
import com.bas.productservice.dto.ProductRequest;
import com.bas.productservice.entity.Category;
import com.bas.productservice.entity.Product;
import com.bas.productservice.exception.ItemAlreadyExist;
import com.bas.productservice.exception.ProductNotFound;
import com.bas.productservice.exception.ResourceAlreadyExistException;
import com.bas.productservice.exception.ResourceNotFoundException;
import com.bas.productservice.helper.CategoryMapperHelper;
import com.bas.productservice.helper.ProductMapperHelper;
import com.bas.productservice.repository.CategoryRepository;
import com.bas.productservice.service.ProductService;
import com.bas.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;
    public List<ProductDTO> showAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map((product -> ProductMapperHelper.map(product))).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> showAllProductsBySubCategoryId(Long id) {
        List<Product> products = productRepository.findBySubCategoryId(id);
        return products.stream().map((product -> ProductMapperHelper.map(product))).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> showAllProductsByCategoryId(Long id) {
        List<Product> products = productRepository.findByCategoryId(  id);
        return products.stream().map((product -> ProductMapperHelper.map(product))).collect(Collectors.toList());
    }

    public ProductDTO findProductByName(String name) throws ProductNotFound {
        Optional<Product> product=productRepository.findByName(name);
        if(product.isEmpty())throw new ProductNotFound("There is no product with this name");
        return ProductDTO.builder()
                .name(product.get().getName())
                .price(product.get().getPrice())
                .description(product.get().getDescription())
                .build();
    }
    public ProductDTO findProductId(Long productId) throws ProductNotFound {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty())throw new ProductNotFound("There is no product with this name");
        return ProductDTO.builder()
                .productId(product.get().getProductId())
                .name(product.get().getName())
                .price(product.get().getPrice())
                .description(product.get().getDescription())
                .imageUrl(product.get().getImageUrl())
                .quantity(product.get().getQuantity())
                .brand(product.get().getBrand())
                .color(product.get().getColor())
                .categoryDto(CategoryDto.builder()
                        .categoryId(product.get().getCategory().getCategoryId())
                        .title(product.get().getCategory().getTitle())
                        .build())
                .build();
    }

    public ProductDTO patchProduct(final Long productId, final ProductDTO productDto) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found with id: " + productId));


        var nameExist = productRepository.existsByName(productDto.getName());
        if(nameExist && !existingProduct.getName().equals(productDto.getName())){
            throw new ItemAlreadyExist("Category with this name already exist.Please change the name.");
        }
        BeanUtils.copyProperties(productDto, existingProduct, "productId", "category");

    //    if (productDto.getCategoryDto() != null) {
      //      existingProduct.setCategory(CategoryMapperHelper.map(productDto.getCategoryDto()));
       // }
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapperHelper.map(updatedProduct);
    }


    public ProductDTO addProduct(ProductRequest productDTO){
        System.out.println("bak"+productDTO.getDynamicFields());
        var productExist = productRepository.existsByName(productDTO.getName());
        if(productExist){
            throw new ItemAlreadyExist("Product already exist");
        }
        try {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("category id","category id", productDTO.getCategoryId()));
            return ProductMapperHelper.map(this.productRepository.save(ProductMapperHelper.map(productDTO,category)));
        } catch (Exception e) {
            throw new ProductNotFound("Error saving product", e);
        }

    }

    public String deleteProduct(Long productId) throws ProductNotFound {
        Optional<Product> product=productRepository.findById(productId);
        if (product.isEmpty())throw new ProductNotFound("There is no product with this name");
        else{
            productRepository.deleteById(product.get().getProductId());
        }
        return "Product deleted successfully";
    }

}
