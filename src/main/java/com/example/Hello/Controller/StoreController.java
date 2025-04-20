package com.example.Hello.Controller;


import com.example.Hello.Service.StoreService;
import com.example.Hello.dto.StoreDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<Page<StoreDTO>> getAllStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(storeService.getAllStores(pageable));
    }

    @GetMapping("/seller/stores")
    public ResponseEntity<Page<StoreDTO>> getStoresBySeller(
            @RequestParam Long sellerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(storeService.getStoresBySeller(sellerId, pageable));
    }

    @PostMapping("/seller/stores")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.createStore(storeDTO));
    }

    @PutMapping("/seller/stores/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @DeleteMapping("/seller/stores/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store deleted");
    }
}
