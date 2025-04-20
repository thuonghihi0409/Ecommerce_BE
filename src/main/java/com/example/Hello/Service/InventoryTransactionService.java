package com.example.Hello.Service;

import com.example.Hello.Mapper.InventoryTransactionMapper;
import com.example.Hello.Repository.InventoryTransactionRepository;
import com.example.Hello.Repository.ProductVariantRepository;
import com.example.Hello.Repository.StoreBranchRepository;
import com.example.Hello.dto.InventoryTransactionDTO;
import com.example.Hello.entity.InventoryTransaction;
import com.example.Hello.entity.ProductVariant;
import com.example.Hello.entity.TransactionType;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InventoryTransactionService {

    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final ProductVariantRepository productVariantRepository;
    private final StoreBranchRepository storeBranchRepository;
    private final InventoryTransactionMapper inventoryTransactionMapper;

    @Transactional
    public InventoryTransactionDTO createInventoryTransaction(InventoryTransactionDTO inventoryTransactionDTO) {
        InventoryTransaction transaction = inventoryTransactionMapper.toEntity(inventoryTransactionDTO);
        transaction.setVariant(productVariantRepository.findById(inventoryTransactionDTO.getVariantId())
                .orElseThrow(() -> new RuntimeException("ProductVariant not found")));
        transaction.setBranch(storeBranchRepository.findById(inventoryTransactionDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found")));
        transaction.setCreatedAt(LocalDateTime.now());

        // Update stock quantity
        ProductVariant variant = transaction.getVariant();
        if (inventoryTransactionDTO.getTransactionType() == TransactionType.IMPORT) {
            variant.setStockQuantity(variant.getStockQuantity() + inventoryTransactionDTO.getQuantity());
        } else {
            if (variant.getStockQuantity() < inventoryTransactionDTO.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }
            variant.setStockQuantity(variant.getStockQuantity() - inventoryTransactionDTO.getQuantity());
        }
        productVariantRepository.save(variant);

        return inventoryTransactionMapper.toDTO(inventoryTransactionRepository.save(transaction));
    }

    public Page<InventoryTransactionDTO> getInventoryTransactionsByBranch(Long branchId, Pageable pageable) {
        return inventoryTransactionRepository.findByBranchBranchId(branchId, pageable).map(inventoryTransactionMapper::toDTO);
    }
}

