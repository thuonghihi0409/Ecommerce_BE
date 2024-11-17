package com.example.Hello.Controller;

import com.example.Hello.Service.ReviewService;
import com.example.Hello.dto.request.ReviewCreationRequest;
import com.example.Hello.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Tạo mới một review
    @PostMapping
    public Review createReview(@RequestBody ReviewCreationRequest request) {
        return reviewService.createReview(request);
    }

    // Lấy tất cả các review
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Lấy một review theo ID
    @GetMapping("/{reviewId}")
    public Review getReviewById(@PathVariable String reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    // Cập nhật một review theo ID
    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable String reviewId, @RequestBody ReviewCreationRequest request) {
        return reviewService.updateReview(reviewId, request);
    }

    // Xóa một review theo ID
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
