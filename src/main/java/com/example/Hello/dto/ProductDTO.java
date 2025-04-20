package com.example.Hello.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private Long storeId;
    private Long categoryId;
    private Long brandId;
    private String productName;
    private String description;
}
