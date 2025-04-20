package com.example.Hello.Mapper;



import com.example.Hello.dto.ProductVariantDTO;
import com.example.Hello.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "branchId", source = "branch.branchId")
    ProductVariantDTO toDTO(ProductVariant productVariant);

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductVariant toEntity(ProductVariantDTO productVariantDTO);
}
