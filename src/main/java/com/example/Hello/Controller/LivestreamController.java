package com.example.Hello.Controller;


import com.example.Hello.Service.LivestreamService;
import com.example.Hello.dto.LivestreamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/livestreams")
@RequiredArgsConstructor
public class LivestreamController {

    private final LivestreamService livestreamService;

    @GetMapping
    public ResponseEntity<Page<LivestreamDTO>> getLivestreams(
            @RequestParam Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(livestreamService.getLivestreamsByStore(storeId, pageable));
    }

    @PostMapping
    public ResponseEntity<LivestreamDTO> createLivestream(@RequestBody LivestreamDTO livestreamDTO) {
        return ResponseEntity.ok(livestreamService.createLivestream(livestreamDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivestreamDTO> updateLivestream(@PathVariable Long id, @RequestBody LivestreamDTO livestreamDTO) {
        return ResponseEntity.ok(livestreamService.updateLivestream(id, livestreamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLivestream(@PathVariable Long id) {
        livestreamService.deleteLivestream(id);
        return ResponseEntity.ok("Livestream deleted");
    }
}
