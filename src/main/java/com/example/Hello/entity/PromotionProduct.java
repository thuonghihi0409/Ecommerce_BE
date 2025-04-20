package com.example.Hello.entity; // Hoặc com.ecommerce.model nếu dùng package của tôi

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "promotion_products")
@IdClass(PromotionProductId.class)
@Getter
@Setter
public class PromotionProduct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Id
    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;
}