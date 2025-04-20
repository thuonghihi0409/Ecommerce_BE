package com.example.Hello.dto;


import com.example.Hello.entity.TransactionType;
import lombok.Data;

@Data
public class InventoryTransactionDTO {
    private Long transactionId;
    private Long variantId;
    private Long branchId;
    private TransactionType transactionType;
    private Integer quantity;
}
