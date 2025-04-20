package com.example.Hello.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long orderItemId;
    private Long variantId;
    private Integer quantity;
    private Double unitPrice;
}
