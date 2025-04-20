package com.example.Hello.dto;


import lombok.Data;

@Data
public class ChatbotInteractionDTO {
    private Long interactionId;
    private Long userId;
    private String userInput;
    private String botResponse;
}