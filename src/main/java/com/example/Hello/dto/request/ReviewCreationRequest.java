package com.example.Hello.dto.request;

import com.example.Hello.entity.RentalProperty;
import com.example.Hello.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReviewCreationRequest {
    private String content;
    private LocalDateTime reviewDateTime;
    private String rentalPropertyId;
    private String userId;
}
