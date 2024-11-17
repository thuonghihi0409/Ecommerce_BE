package com.example.Hello.Repository;

import com.example.Hello.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Review_Repository extends JpaRepository<Review,String> {
}
