package com.example.Hello.Mapper;


import com.example.Hello.dto.BrandDTO;
import com.example.Hello.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandDTO toDTO(Brand brand);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Brand toEntity(BrandDTO brandDTO);
}