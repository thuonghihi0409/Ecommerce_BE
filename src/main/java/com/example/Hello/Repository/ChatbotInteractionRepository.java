package com.example.Hello.Repository;


import com.example.Hello.entity.ChatbotInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotInteractionRepository extends JpaRepository<ChatbotInteraction, Long> {
    Page<ChatbotInteraction> findByUserUserId(Long userId, Pageable pageable);
}
