package com.example.Hello.Controller;

import com.example.Hello.Service.ConversationService;
import com.example.Hello.dto.request.ConversationCreationRequest;
import com.example.Hello.dto.respon.ConversationRespon;
import com.example.Hello.entity.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;
    @PostMapping
    Conversation createConversation (@RequestBody ConversationCreationRequest request){
        return conversationService.createConversation(request);
    }
    @GetMapping
    List<Conversation> getConversations (){
        return conversationService.getConversations();
    }

    @GetMapping("/userId={userId}")
    List<Conversation> getConversationsByUserId (@PathVariable String userId){
        return conversationService.getConversationsByUserId(userId);
    }
    @DeleteMapping("/conversationid={id}")
    String deleteConversationById (@PathVariable String id){
        conversationService.deleteConversationById(id);
        return "conversation has been deleta";
    }
}
