package com.example.Hello.dto;


import lombok.Data;

@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Long parentCategoryId;
}
