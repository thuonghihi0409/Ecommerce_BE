package com.example.Hello.Mapper;


import com.example.Hello.dto.CategoryDTO;
import com.example.Hello.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "parentCategoryId", source = "parentCategory.categoryId")
    CategoryDTO toDTO(Category category);

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);
}

