package com.example.Hello.Mapper;

import com.example.Hello.dto.OrderItemDTO;
import com.example.Hello.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "variantId", source = "variant.variantId")
    OrderItemDTO toDTO(OrderItem orderItem);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "variant", ignore = true)
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}
