package com.example.Hello.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Livestreams")
@Data
public class Livestream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long livestreamId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String title;
    private String streamUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

