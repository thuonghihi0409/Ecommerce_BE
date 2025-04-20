package com.example.Hello.Controller;


import com.example.Hello.Service.ChatbotService;
import com.example.Hello.dto.ChatbotInteractionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping
    public ResponseEntity<ChatbotInteractionDTO> interactWithChatbot(@RequestBody ChatbotInteractionDTO interactionDTO) {
        return ResponseEntity.ok(chatbotService.interactWithChatbot(interactionDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ChatbotInteractionDTO>> getChatbotHistory(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(chatbotService.getChatbotHistory(userId, pageable));
    }
}
