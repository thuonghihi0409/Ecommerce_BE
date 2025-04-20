package com.example.Hello.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PromotionDTO {
    private Long promotionId;
    private Long storeId;
    private String promotionName;
    private Double discountPercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> variantIds;
}
