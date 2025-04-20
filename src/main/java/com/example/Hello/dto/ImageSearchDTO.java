package com.example.Hello.dto;


import lombok.Data;

@Data
public class ImageSearchDTO {
    private Long searchId;
    private Long userId;
    private String imageUrl;
    private String searchResult;
}

