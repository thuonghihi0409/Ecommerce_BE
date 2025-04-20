package com.example.Hello.Mapper;


import com.example.Hello.dto.StoreDTO;
import com.example.Hello.entity.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    @Mapping(target = "sellerId", source = "seller.userId")
    StoreDTO toDTO(Store store);

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Store toEntity(StoreDTO storeDTO);
}