package com.example.Hello.Service;


import com.example.Hello.Mapper.ProductMapper;
import com.example.Hello.Repository.ProductRepository;
import com.example.Hello.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductDTO> getRecommendations(Long userId, Pageable pageable) {
        // Mock recommendation logic (replace with actual AI integration)
        return productRepository.findAll(pageable).map(productMapper::toDTO);
    }
}
