package com.example.Hello.Service;

import com.example.Hello.Repository.RentalProperty_Repository;
import com.example.Hello.Repository.Review_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.ReviewCreationRequest;
import com.example.Hello.entity.RentalProperty;
import com.example.Hello.entity.Review;
import com.example.Hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private Review_Repository reviewRepository;

    @Autowired
    private RentalProperty_Repository rentalPropertyRepository;

    @Autowired
    private User_Repository userRepository;

    // Tạo mới một review
    public Review createReview(ReviewCreationRequest request) {
        RentalProperty rentalProperty = rentalPropertyRepository.findById(request.getRentalPropertyId())
                .orElseThrow(() -> new RuntimeException("Rental Property not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review();
        review.setContent(request.getContent());
        review.setReviewDateTime(request.getReviewDateTime());
        review.setRentalProperty(rentalProperty);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    // Lấy tất cả review
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Lấy review theo ID
    public Review getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    // Cập nhật một review
    public Review updateReview(String reviewId, ReviewCreationRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        RentalProperty rentalProperty = rentalPropertyRepository.findById(request.getRentalPropertyId())
                .orElseThrow(() -> new RuntimeException("Rental Property not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        review.setContent(request.getContent());
        review.setReviewDateTime(request.getReviewDateTime());
        review.setRentalProperty(rentalProperty);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    // Xóa một review
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
