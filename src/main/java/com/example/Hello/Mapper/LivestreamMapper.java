package com.example.Hello.Mapper;


import com.example.Hello.dto.LivestreamDTO;
import com.example.Hello.entity.Livestream;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LivestreamMapper {
    @Mapping(target = "storeId", source = "store.storeId")
    LivestreamDTO toDTO(Livestream livestream);

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Livestream toEntity(LivestreamDTO livestreamDTO);
}
