package com.example.Hello.Service;


import com.example.Hello.Mapper.PromotionMapper;
import com.example.Hello.Repository.ProductVariantRepository;
import com.example.Hello.Repository.PromotionProductRepository;
import com.example.Hello.Repository.PromotionRepository;
import com.example.Hello.Repository.StoreRepository;
import com.example.Hello.dto.PromotionDTO;
import com.example.Hello.entity.Promotion;
import com.example.Hello.entity.PromotionProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionProductRepository promotionProductRepository;
    private final StoreRepository storeRepository;
    private final ProductVariantRepository productVariantRepository;
    private final PromotionMapper promotionMapper;

    @Transactional
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion.setStore(storeRepository.findById(promotionDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        promotion.setCreatedAt(LocalDateTime.now());
        promotion.setUpdatedAt(LocalDateTime.now());
        Promotion savedPromotion = promotionRepository.save(promotion);

        for (Long variantId : promotionDTO.getVariantIds()) {
            PromotionProduct promotionProduct = new PromotionProduct();
            promotionProduct.setPromotion(savedPromotion);
            promotionProduct.setVariant(productVariantRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("ProductVariant not found")));
            promotionProductRepository.save(promotionProduct);
        }

        return promotionMapper.toDTO(savedPromotion);
    }

    @Transactional
    public PromotionDTO updatePromotion(Long id, PromotionDTO promotionDTO) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        promotion.setStore(storeRepository.findById(promotionDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found")));
        promotion.setPromotionName(promotionDTO.getPromotionName());
        promotion.setDiscountPercentage(promotionDTO.getDiscountPercentage());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setUpdatedAt(LocalDateTime.now());

        // Delete existing promotion products
        promotionProductRepository.deleteByPromotionPromotionId(id);

        // Add new promotion products
        for (Long variantId : promotionDTO.getVariantIds()) {
            PromotionProduct promotionProduct = new PromotionProduct();
            promotionProduct.setPromotion(promotion);
            promotionProduct.setVariant(productVariantRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("ProductVariant not found")));
            promotionProductRepository.save(promotionProduct);
        }

        return promotionMapper.toDTO(promotionRepository.save(promotion));
    }

    public void deletePromotion(Long id) {
        promotionProductRepository.deleteByPromotionPromotionId(id);
        promotionRepository.deleteById(id);
    }

    public PromotionDTO getPromotionById(Long id) {
        return promotionMapper.toDTO(promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found")));
    }

    public Page<PromotionDTO> getPromotionsByStore(Long storeId, Pageable pageable) {
        return promotionRepository.findByStoreStoreId(storeId, pageable).map(promotionMapper::toDTO);
    }
}

