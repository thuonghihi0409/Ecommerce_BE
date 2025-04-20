package com.example.Hello.dto;


import lombok.Data;

@Data
public class ProductVariantDTO {
    private Long variantId;
    private Long productId;
    private String sku;
    private String color;
    private String configuration;
    private Double price;
    private Integer stockQuantity;
    private Long branchId;
}
