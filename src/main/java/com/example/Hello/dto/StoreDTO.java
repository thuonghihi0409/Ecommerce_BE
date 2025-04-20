package com.example.Hello.dto;


import lombok.Data;

@Data
public class StoreDTO {
    private Long storeId;
    private Long sellerId;
    private String storeName;
    private String description;
    private String logoUrl;
}