package com.example.Hello.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Conversation {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String conversationId;
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;



}

