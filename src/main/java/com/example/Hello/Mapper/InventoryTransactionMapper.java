package com.example.Hello.Mapper;


import com.example.Hello.dto.InventoryTransactionDTO;
import com.example.Hello.entity.InventoryTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryTransactionMapper {
    @Mapping(target = "variantId", source = "variant.variantId")
    @Mapping(target = "branchId", source = "branch.branchId")
    InventoryTransactionDTO toDTO(InventoryTransaction inventoryTransaction);

    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "branch", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    InventoryTransaction toEntity(InventoryTransactionDTO inventoryTransactionDTO);
}
