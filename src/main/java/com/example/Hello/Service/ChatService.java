package com.example.Hello.Service;


import com.example.Hello.Mapper.ChatMessageMapper;
import com.example.Hello.Repository.ChatMessageRepository;
import com.example.Hello.Repository.UserRepository;
import com.example.Hello.dto.ChatMessageDTO;
import com.example.Hello.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
        chatMessage.setSender(userRepository.findById(chatMessageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found")));
        chatMessage.setReceiver(userRepository.findById(chatMessageDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found")));
        chatMessage.setSentAt(LocalDateTime.now());
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // Send message via WebSocket
        messagingTemplate.convertAndSend("/topic/messages/" + chatMessageDTO.getReceiverId(), chatMessageDTO);
        return chatMessageMapper.toDTO(savedMessage);
    }

    public Page<ChatMessageDTO> getChatHistory(Long senderId, Long receiverId, Pageable pageable) {
        return chatMessageRepository
                .findBySenderUserIdAndReceiverUserIdOrSenderUserIdAndReceiverUserId(
                        senderId, receiverId, receiverId, senderId, pageable)
                .map(chatMessageMapper::toDTO);
    }
}