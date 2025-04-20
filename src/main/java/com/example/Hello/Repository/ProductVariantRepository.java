package com.example.Hello.Repository;

import com.example.Hello.entity.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Page<ProductVariant> findByProductProductId(Long productId, Pageable pageable);
}

