package com.example.Hello.Repository;


import com.example.Hello.entity.PromotionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Long> {
    void deleteByPromotionPromotionId(Long promotionId);
}

