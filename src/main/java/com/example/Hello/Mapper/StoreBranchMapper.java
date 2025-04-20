package com.example.Hello.Mapper;


import com.example.Hello.dto.StoreBranchDTO;
import com.example.Hello.entity.StoreBranch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreBranchMapper {
    @Mapping(target = "storeId", source = "store.storeId")
    StoreBranchDTO toDTO(StoreBranch storeBranch);

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    StoreBranch toEntity(StoreBranchDTO storeBranchDTO);
}
