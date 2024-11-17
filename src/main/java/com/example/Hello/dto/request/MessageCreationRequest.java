package com.example.Hello.dto.request;

import com.example.Hello.entity.Conversation;
import com.example.Hello.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageCreationRequest {
    private String content;
    private String messageStatus;
    private Date timesend;
    private String conversationId;
    private String userId;

}
