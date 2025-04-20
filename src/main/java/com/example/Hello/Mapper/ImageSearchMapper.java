package com.example.Hello.Mapper;



import com.example.Hello.dto.ImageSearchDTO;
import com.example.Hello.entity.ImageSearch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageSearchMapper {
    @Mapping(target = "userId", source = "user.userId")
    ImageSearchDTO toDTO(ImageSearch imageSearch);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ImageSearch toEntity(ImageSearchDTO imageSearchDTO);
}

