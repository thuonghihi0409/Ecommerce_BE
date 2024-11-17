package com.example.Hello.Repository;

import com.example.Hello.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Message_Repository extends JpaRepository<Message, String> {

    List<Message> findByConversation_ConversationId(String conversationId);

    @Query("SELECT m FROM Message m WHERE m.conversation.conversationId = :conversationId ORDER BY m.timesend DESC")
    List<Message> findFinalByConversation_ConversationIdOrderByTimesendDesc(String conversationId);
    void deleteAllByConversation_ConversationId(String conversationId);
}

