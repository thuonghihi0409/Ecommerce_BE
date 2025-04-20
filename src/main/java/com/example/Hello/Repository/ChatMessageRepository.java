package com.example.Hello.Repository;


import com.example.Hello.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findBySenderUserIdAndReceiverUserIdOrSenderUserIdAndReceiverUserId(
            Long senderId1, Long receiverId1, Long senderId2, Long receiverId2, Pageable pageable);
}

