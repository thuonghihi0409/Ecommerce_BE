package com.example.Hello.Controller;


import com.example.Hello.Service.ProductVariantService;
import com.example.Hello.dto.ProductVariantDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<Page<ProductVariantDTO>> getProductVariants(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productVariantService.getProductVariantsByProduct(productId, pageable));
    }

    @PostMapping
    public ResponseEntity<ProductVariantDTO> createProductVariant(@RequestBody ProductVariantDTO productVariantDTO) {
        return ResponseEntity.ok(productVariantService.createProductVariant(productVariantDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariantDTO> updateProductVariant(@PathVariable Long id, @RequestBody ProductVariantDTO productVariantDTO) {
        return ResponseEntity.ok(productVariantService.updateProductVariant(id, productVariantDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteProductVariant(id);
        return ResponseEntity.ok("ProductVariant deleted");
    }
}
