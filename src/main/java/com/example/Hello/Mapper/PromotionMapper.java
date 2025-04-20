package com.example.Hello.Mapper;


import com.example.Hello.dto.PromotionDTO;
import com.example.Hello.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    @Mapping(target = "storeId", source = "store.storeId")
    @Mapping(target = "variantIds", ignore = true)
    PromotionDTO toDTO(Promotion promotion);

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Promotion toEntity(PromotionDTO promotionDTO);
}