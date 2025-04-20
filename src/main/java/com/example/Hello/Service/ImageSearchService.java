package com.example.Hello.Service;


import com.example.Hello.Mapper.ImageSearchMapper;
import com.example.Hello.Repository.ImageSearchRepository;
import com.example.Hello.Repository.UserRepository;
import com.example.Hello.dto.ImageSearchDTO;
import com.example.Hello.entity.ImageSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImageSearchService {

    private final ImageSearchRepository imageSearchRepository;
    private final UserRepository userRepository;
    private final ImageSearchMapper imageSearchMapper;

    public ImageSearchDTO performImageSearch(ImageSearchDTO imageSearchDTO) {
        ImageSearch imageSearch = imageSearchMapper.toEntity(imageSearchDTO);
        imageSearch.setUser(userRepository.findById(imageSearchDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        // Mock image search result (replace with actual AI integration)
        imageSearch.setSearchResult("Mock result: Found products matching image");
        imageSearch.setCreatedAt(LocalDateTime.now());
        return imageSearchMapper.toDTO(imageSearchRepository.save(imageSearch));
    }

    public Page<ImageSearchDTO> getImageSearchHistory(Long userId, Pageable pageable) {
        return imageSearchRepository.findByUserUserId(userId, pageable).map(imageSearchMapper::toDTO);
    }
}
