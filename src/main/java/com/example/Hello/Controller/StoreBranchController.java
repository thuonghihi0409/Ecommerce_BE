package com.example.Hello.Controller;


import com.example.Hello.Service.StoreBranchService;
import com.example.Hello.dto.StoreBranchDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/branches")
@RequiredArgsConstructor
public class StoreBranchController {

    private final StoreBranchService storeBranchService;

    @GetMapping
    public ResponseEntity<Page<StoreBranchDTO>> getStoreBranches(
            @RequestParam Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(storeBranchService.getStoreBranchesByStore(storeId, pageable));
    }

    @PostMapping
    public ResponseEntity<StoreBranchDTO> createStoreBranch(@RequestBody StoreBranchDTO storeBranchDTO) {
        return ResponseEntity.ok(storeBranchService.createStoreBranch(storeBranchDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreBranchDTO> updateStoreBranch(@PathVariable Long id, @RequestBody StoreBranchDTO storeBranchDTO) {
        return ResponseEntity.ok(storeBranchService.updateStoreBranch(id, storeBranchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStoreBranch(@PathVariable Long id) {
        storeBranchService.deleteStoreBranch(id);
        return ResponseEntity.ok("StoreBranch deleted");
    }
}
