package com.example.Hello.Service;

import com.example.Hello.Repository.Conversation_Repository;
import com.example.Hello.Repository.Message_Repository;
import com.example.Hello.Repository.User_Repository;
import com.example.Hello.dto.request.ConversationCreationRequest;
import com.example.Hello.dto.respon.ConversationRespon;
import com.example.Hello.entity.Conversation;
import com.example.Hello.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    @Autowired
    private Conversation_Repository conversationRepository;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Message_Repository messageRepository;
    public Conversation createConversation (ConversationCreationRequest request){
        Conversation conversation= new Conversation();
        User user1 = userRepository.findById(request.getUser1Id()).orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = userRepository.findById(request.getUser2Id()).orElseThrow(() -> new RuntimeException("User not found"));
        conversation.setUser1(user1);
        conversation.setUser2(user2);
        return conversationRepository.save(conversation);
    }
    public List<Conversation> getConversations (){
        return conversationRepository.findAll();
    }

    public List<Conversation> getConversationsByUserId(String userId) {
        return conversationRepository.findByUser1_IdOrUser2_Id(userId,userId);
    }
    @Transactional
    public void deleteConversationById(String id){
        messageRepository.deleteAllByConversation_ConversationId(id);
        conversationRepository.deleteById(id);
    }

}
