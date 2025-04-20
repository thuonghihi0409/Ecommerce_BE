package com.example.Hello.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Product_Variants")
@Data
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(unique = true)
    private String sku;

    private String color;
    private String configuration;
    private Double price;
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private StoreBranch branch;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
