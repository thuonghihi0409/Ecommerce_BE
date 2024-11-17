package com.example.Hello.Mapper;

import com.example.Hello.dto.request.RentalPropertyCreationRequest;
import com.example.Hello.entity.RentalProperty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RentalPropertyMapper {
    RentalPropertyMapper INSTANCE = Mappers.getMapper(RentalPropertyMapper.class);

    RentalProperty toEntity(RentalPropertyCreationRequest request);
}
