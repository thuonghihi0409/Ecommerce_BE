package com.example.Hello.Service;

import com.example.Hello.Mapper.ProductMapper;
import com.example.Hello.Repository.BrandRepository;
import com.example.Hello.Repository.CategoryRepository;
import com.example.Hello.Repository.ProductRepository;
import com.example.Hello.Repository.StoreRepository;
import com.example.Hello.dto.ProductDTO;
import com.example.Hello.entity.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final StoreRepository storeRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final BrandRepository brandRepository;
    @Autowired
    private final ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        product.setStore(storeRepository.findById(productDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product.setBrand(brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productMapper.toDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStore(storeRepository.findById(productDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));
        product.setBrand(brandRepository.findById(productDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found")));
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setUpdatedAt(LocalDateTime.now());
        return productMapper.toDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProductById(Long id) {
        return productMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    public Page<ProductDTO> searchProducts(String keyword, Long categoryId, Long brandId, Pageable pageable) {
        if (keyword != null) {
            return productRepository.findByProductNameContaining(keyword, pageable).map(productMapper::toDTO);
        } else if (categoryId != null) {
            return productRepository.findByCategoryCategoryId(categoryId, pageable).map(productMapper::toDTO);
        } else if (brandId != null) {
            return productRepository.findByBrandBrandId(brandId, pageable).map(productMapper::toDTO);
        }
        return productRepository.findAll(pageable).map(productMapper::toDTO);
    }
}