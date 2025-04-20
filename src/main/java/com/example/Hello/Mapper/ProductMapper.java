package com.example.Hello.Mapper;

import com.example.Hello.dto.ProductDTO;
import com.example.Hello.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "storeId", source = "store.storeId")
    @Mapping(target = "categoryId", source = "category.categoryId")
    @Mapping(target = "brandId", source = "brand.brandId")
    ProductDTO toDTO(Product product);

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductDTO productDTO);
}

