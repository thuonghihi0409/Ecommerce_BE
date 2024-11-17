package com.example.Hello.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reviewId;
    private String content;
    private LocalDateTime reviewDateTime;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private RentalProperty rentalProperty;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public String addReview() {
        // logic
        return "Review added!";
    }

    public void deleteReview() {
        // logic
    }

    public String editReview() {
        // logic
        return "Review updated!";
    }

}

