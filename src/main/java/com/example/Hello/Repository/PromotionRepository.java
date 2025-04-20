package com.example.Hello.Repository;


import com.example.Hello.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Page<Promotion> findByStoreStoreId(Long storeId, Pageable pageable);
}
