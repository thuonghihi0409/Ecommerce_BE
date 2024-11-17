package com.example.Hello.Controller;

import com.example.Hello.Service.MessageService;
import com.example.Hello.dto.request.MessageCreationRequest;
import com.example.Hello.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService ;
    @PostMapping
    Message createMessage (@RequestBody MessageCreationRequest request){
        return messageService.createMessage(request);
    }
    @GetMapping
    List<Message> getMessages (){
        return messageService.getMessages();
    }
    @GetMapping("/conversationId={conversationId}")
    List<Message> getMessagesByConversationId (@PathVariable String conversationId){
        return messageService.getMessagesByConversationId(conversationId);
    }
    @GetMapping("/lastmessage/conversationId={conversationId}")
    Message getlastMessage (@PathVariable String conversationId){
        return  messageService.getLastMessageByConversationId(conversationId);

    }
}
