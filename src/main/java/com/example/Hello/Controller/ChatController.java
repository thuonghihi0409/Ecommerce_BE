package com.example.Hello.Controller;

import com.example.Hello.Service.ChatService;
import com.example.Hello.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/buyer/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatMessageDTO> sendMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        return ResponseEntity.ok(chatService.sendMessage(chatMessageDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ChatMessageDTO>> getChatHistory(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chatService.getChatHistory(senderId, receiverId, pageable));
    }

    @MessageMapping("/chat.send")
    public void handleWebSocketMessage(@Payload ChatMessageDTO chatMessageDTO) {
        chatService.sendMessage(chatMessageDTO);
    }
}


