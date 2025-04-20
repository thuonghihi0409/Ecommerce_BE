package com.example.Hello.Service;


import com.example.Hello.Mapper.ReviewMapper;
import com.example.Hello.Repository.ProductRepository;
import com.example.Hello.Repository.ReviewRepository;
import com.example.Hello.Repository.UserRepository;
import com.example.Hello.dto.ReviewDTO;
import com.example.Hello.entity.Review;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        review.setProduct(productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found")));
        review.setBuyer(userRepository.findById(reviewDTO.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found")));
        review.setCreatedAt(LocalDateTime.now());
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public ReviewDTO getReviewById(Long id) {
        return reviewMapper.toDTO(reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found")));
    }

    public Page<ReviewDTO> getReviewsByProduct(Long productId, Pageable pageable) {
        return reviewRepository.findByProductProductId(productId, pageable).map(reviewMapper::toDTO);
    }
}
