package com.example.Hello.dto.respon;

import com.example.Hello.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationRespon {

    private String conversationId;

    private User user1;

    private User user2;

    private  String lastMessage ;

}

