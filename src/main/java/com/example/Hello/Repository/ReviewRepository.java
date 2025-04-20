package com.example.Hello.Repository;


import com.example.Hello.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByProductProductId(Long productId, Pageable pageable);
}
