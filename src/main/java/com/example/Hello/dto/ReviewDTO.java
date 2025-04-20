package com.example.Hello.dto;


import lombok.Data;

@Data
public class ReviewDTO {
    private Long reviewId;
    private Long productId;
    private Long buyerId;
    private Integer rating;
    private String comment;
}
