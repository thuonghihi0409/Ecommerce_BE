package com.example.Hello.Service;

import com.example.Hello.Repository.Conversation_Repository;
import com.example.Hello.Repository.Message_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.MessageCreationRequest;
import com.example.Hello.entity.Conversation;
import com.example.Hello.entity.Message;
import com.example.Hello.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private Message_Repository messageRepository;
    @Autowired
    private Conversation_Repository conversationRepository;
    @Autowired
    private User_Repository userRepository;
    public Message createMessage (MessageCreationRequest request){
        System.out.println("===============================" + request.getTimesend());
        Message message= new Message();
        User user=userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Conversation conversation= conversationRepository.findById(request.getConversationId()).orElseThrow(() -> new RuntimeException("User not found"));
        message.setMessageStatus(request.getMessageStatus());
        message.setContent(request.getContent());
        message.setTimesend(request.getTimesend());
        message.setUser(user);
        message.setConversation(conversation);
        return  messageRepository.save(message);
    }
    public List<Message> getMessages (){
        return messageRepository.findAll();
    }
    public List<Message> getMessagesByConversationId(String conversationId) {
        return messageRepository.findByConversation_ConversationId(conversationId);
    }
    public Message getLastMessageByConversationId(String conversationId) {

        return messageRepository.findFinalByConversation_ConversationIdOrderByTimesendDesc(conversationId).get(0);
    }
}
