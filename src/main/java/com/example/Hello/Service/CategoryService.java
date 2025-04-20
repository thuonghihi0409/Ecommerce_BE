package com.example.Hello.Service;


import com.example.Hello.Mapper.CategoryMapper;
import com.example.Hello.Repository.CategoryRepository;
import com.example.Hello.dto.CategoryDTO;
import com.example.Hello.entity.Category;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        if (categoryDTO.getParentCategoryId() != null) {
            category.setParentCategory(categoryRepository.findById(categoryDTO.getParentCategoryId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found")));
        }
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setCategoryName(categoryDTO.getCategoryName());
        if (categoryDTO.getParentCategoryId() != null) {
            category.setParentCategory(categoryRepository.findById(categoryDTO.getParentCategoryId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found")));
        } else {
            category.setParentCategory(null);
        }
        category.setUpdatedAt(LocalDateTime.now());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryById(Long id) {
        return categoryMapper.toDTO(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found")));
    }

    public Page<CategoryDTO> getCategories(Pageable pageable) {
        return categoryRepository.findByParentCategoryIsNull(pageable).map(categoryMapper::toDTO);
    }
}