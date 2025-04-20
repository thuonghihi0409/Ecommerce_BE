package com.example.Hello.Mapper;


import com.example.Hello.dto.OrderDTO;
import com.example.Hello.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "buyerId", source = "buyer.userId")
    @Mapping(target = "storeId", source = "store.storeId")
    @Mapping(target = "branchId", source = "branch.branchId")
    OrderDTO toDTO(Order order);

    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Order toEntity(OrderDTO orderDTO);
}
