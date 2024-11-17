package com.example.Hello.dto.request;

import com.example.Hello.entity.Customer;
import com.example.Hello.entity.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationCreationRequest {
   private String user1Id;
   private String user2Id;
}
