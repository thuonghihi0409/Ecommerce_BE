package com.example.Hello.Controller;


import com.example.Hello.Service.InventoryTransactionService;
import com.example.Hello.dto.InventoryTransactionDTO;
import com.example.Hello.entity.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/inventory")
@RequiredArgsConstructor
public class InventoryTransactionController {

    private final InventoryTransactionService inventoryTransactionService;

    @GetMapping
    public ResponseEntity<Page<InventoryTransactionDTO>> getInventoryTransactions(
            @RequestParam Long branchId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inventoryTransactionService.getInventoryTransactionsByBranch(branchId, pageable));
    }

    @PostMapping("/import")
    public ResponseEntity<InventoryTransactionDTO> importInventory(@RequestBody InventoryTransactionDTO inventoryTransactionDTO) {
        inventoryTransactionDTO.setTransactionType(TransactionType.IMPORT);
        return ResponseEntity.ok(inventoryTransactionService.createInventoryTransaction(inventoryTransactionDTO));
    }

    @PostMapping("/export")
    public ResponseEntity<InventoryTransactionDTO> exportInventory(@RequestBody InventoryTransactionDTO inventoryTransactionDTO) {
        inventoryTransactionDTO.setTransactionType(TransactionType.EXPORT);
        return ResponseEntity.ok(inventoryTransactionService.createInventoryTransaction(inventoryTransactionDTO));
    }
}

