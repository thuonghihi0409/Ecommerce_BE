package com.example.Hello.Service;


import com.example.Hello.Mapper.ChatbotInteractionMapper;
import com.example.Hello.Repository.ChatbotInteractionRepository;
import com.example.Hello.Repository.UserRepository;
import com.example.Hello.dto.ChatbotInteractionDTO;
import com.example.Hello.entity.ChatbotInteraction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ChatbotInteractionRepository chatbotInteractionRepository;
    private final UserRepository userRepository;
    private final ChatbotInteractionMapper chatbotInteractionMapper;

    public ChatbotInteractionDTO interactWithChatbot(ChatbotInteractionDTO interactionDTO) {
        ChatbotInteraction interaction = chatbotInteractionMapper.toEntity(interactionDTO);
        interaction.setUser(userRepository.findById(interactionDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        // Mock chatbot response (replace with actual AI integration)
        interaction.setBotResponse("Mock response: How can I assist you with " + interactionDTO.getUserInput() + "?");
        interaction.setCreatedAt(LocalDateTime.now());
        return chatbotInteractionMapper.toDTO(chatbotInteractionRepository.save(interaction));
    }

    public Page<ChatbotInteractionDTO> getChatbotHistory(Long userId, Pageable pageable) {
        return chatbotInteractionRepository.findByUserUserId(userId, pageable).map(chatbotInteractionMapper::toDTO);
    }
}
