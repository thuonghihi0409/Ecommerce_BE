package com.example.Hello.Service;


import com.example.Hello.Mapper.ProductVariantMapper;
import com.example.Hello.Repository.ProductRepository;
import com.example.Hello.Repository.ProductVariantRepository;
import com.example.Hello.Repository.StoreBranchRepository;
import com.example.Hello.dto.ProductVariantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.Hello.entity.ProductVariant;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final StoreBranchRepository storeBranchRepository;
    private final ProductVariantMapper productVariantMapper;

    public ProductVariantDTO createProductVariant(ProductVariantDTO productVariantDTO) {
        ProductVariant productVariant = productVariantMapper.toEntity(productVariantDTO);
        productVariant.setProduct(productRepository.findById(productVariantDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        productVariant.setBranch(storeBranchRepository.findById(productVariantDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found")));
        productVariant.setCreatedAt(LocalDateTime.now());
        productVariant.setUpdatedAt(LocalDateTime.now());
        return productVariantMapper.toDTO(productVariantRepository.save(productVariant));
    }

    public ProductVariantDTO updateProductVariant(Long id, ProductVariantDTO productVariantDTO) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariant not found"));
        productVariant.setProduct(productRepository.findById(productVariantDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        productVariant.setBranch(storeBranchRepository.findById(productVariantDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found")));
        productVariant.setSku(productVariantDTO.getSku());
        productVariant.setColor(productVariantDTO.getColor());
        productVariant.setConfiguration(productVariantDTO.getConfiguration());
        productVariant.setPrice(productVariantDTO.getPrice());
        productVariant.setStockQuantity(productVariantDTO.getStockQuantity());
        productVariant.setUpdatedAt(LocalDateTime.now());
        return productVariantMapper.toDTO(productVariantRepository.save(productVariant));
    }

    public void deleteProductVariant(Long id) {
        productVariantRepository.deleteById(id);
    }

    public ProductVariantDTO getProductVariantById(Long id) {
        return productVariantMapper.toDTO(productVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariant not found")));
    }

    public Page<ProductVariantDTO> getProductVariantsByProduct(Long productId, Pageable pageable) {
        return productVariantRepository.findByProductProductId(productId, pageable).map(productVariantMapper::toDTO);
    }
}

