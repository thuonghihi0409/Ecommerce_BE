package com.example.Hello.Controller;

import com.example.Hello.Repository.Conversation_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.Service.MessageService;
import com.example.Hello.dto.request.MessageCreationRequest;
import com.example.Hello.entity.Conversation;
import com.example.Hello.entity.Message;
import com.example.Hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    MessageService messageService ;
    @Autowired
    User_Repository userRepository;
    @Autowired
    Conversation_Repository conversationRepository;
    @MessageMapping("/chat.sendMessage/{receiverId}") // Nhận ID người nhận
    @SendTo("/topic/user/{receiverId}") // Gửi tới kênh riêng của người nhận
    public Message sendMessage(@DestinationVariable String receiverId, MessageCreationRequest request) {
        // Xử lý tin nhắn (lưu vào DB, v.v.)
        Message message= new Message();
        User user=userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Conversation conversation= conversationRepository.findById(request.getConversationId()).orElseThrow(() -> new RuntimeException("User not found"));
        message.setMessageStatus(request.getMessageStatus());
        message.setContent(request.getContent());
        message.setTimesend(request.getTimesend());
        message.setUser(user);
        message.setConversation(conversation);
        System.out.println("Received message: " + message.getContent() + "Time : " + message.getTimesend() + " for receiver: " + receiverId);
        return message; // Gửi tin nhắn đã xử lý tới người nhận
    }
}
