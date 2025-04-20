package com.example.Hello.entity; // Hoặc com.ecommerce.model nếu dùng package của tôi

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class PromotionProductId implements Serializable {

    private Long promotion;
    private Long variant;

    // Constructor mặc định
    public PromotionProductId() {}

    // Constructor với tham số
    public PromotionProductId(Long promotion, Long variant) {
        this.promotion = promotion;
        this.variant = variant;
    }

    // Override equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionProductId that = (PromotionProductId) o;
        return Objects.equals(promotion, that.promotion) &&
                Objects.equals(variant, that.variant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promotion, variant);
    }
}