package com.example.Hello.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Long messageId;
    private Long senderId;
    private Long receiverId;
    private String content;
}

