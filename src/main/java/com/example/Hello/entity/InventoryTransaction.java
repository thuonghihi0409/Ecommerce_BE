package com.example.Hello.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Inventory_Transactions")
@Data
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private StoreBranch branch;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Integer quantity;
    private LocalDateTime createdAt;
}

