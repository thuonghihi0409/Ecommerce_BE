package com.example.Hello.Controller;


import com.example.Hello.Service.ImageSearchService;
import com.example.Hello.dto.ImageSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer/image-search")
@RequiredArgsConstructor
public class ImageSearchController {

    private final ImageSearchService imageSearchService;

    @PostMapping
    public ResponseEntity<ImageSearchDTO> performImageSearch(@RequestBody ImageSearchDTO imageSearchDTO) {
        return ResponseEntity.ok(imageSearchService.performImageSearch(imageSearchDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ImageSearchDTO>> getImageSearchHistory(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(imageSearchService.getImageSearchHistory(userId, pageable));
    }
}
