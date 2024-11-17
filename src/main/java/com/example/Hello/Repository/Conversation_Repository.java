package com.example.Hello.Repository;

import com.example.Hello.dto.respon.ConversationRespon;
import com.example.Hello.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Conversation_Repository extends JpaRepository<Conversation, String> {

    List<Conversation> findByUser1_IdOrUser2_Id(String userId1, String userId2);
    void deleteAllByUser1_IdOrUser2_Id(String userId1, String userId2);

}
