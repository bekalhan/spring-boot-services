package com.bas.productservice.service.impl;

import com.bas.productservice.dto.ProductDto;
import com.bas.productservice.entity.Product;
import com.bas.productservice.exception.ResourceAlreadyExistException;
import com.bas.productservice.exception.ResourceNotFoundException;
import com.bas.productservice.helper.ProductMapperHelper;
import com.bas.productservice.repository.CategoryRepository;
import com.bas.productservice.repository.ProductRepository;
import com.bas.productservice.response.ProductResponse;
import com.bas.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse getAllProductsWithPageableAndSorting(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        // get content for page object
        List<Product> listOfPosts = products.getContent();

        List<ProductDto> content= listOfPosts.stream().map(ProductMapperHelper::map).collect(Collectors.toList());

        return ProductMapperHelper.mapProductResponse(content,products);

    }

    @Override
    public List<ProductDto> getAllProducts() {
               return this.productRepository.findAll()
                .stream()
                .map(ProductMapperHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ProductDto getProductById(final Long productId) {
        return this.productRepository.findById(productId)
                .map(ProductMapperHelper::map)
                .orElseThrow(() -> new ResourceNotFoundException("Product","id", productId));
    }

    @Override
    public ProductDto createProduct(final ProductDto productDto) {
        var productExist = productRepository.existsByTitle(productDto.getTitle());
        if(productExist) throw new ResourceAlreadyExistException("Product","title",productDto.getTitle());

        var category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category","id",productDto.getCategoryId()));

        return ProductMapperHelper.map(this.productRepository
                .save(ProductMapperHelper.map(productDto,category)));
    }

    @Override
    public ProductDto patchProduct(final Long productId, final ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));

        Product updatedProduct = ProductMapperHelper.map(productDto, product.getCategory());

        Product savedProduct = productRepository.save(updatedProduct);

        return ProductMapperHelper.map(savedProduct);
    }

    @Override
    public void deleteProduct(final Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("product","id",productId));
        this.productRepository.deleteById(productId);
    }

}
