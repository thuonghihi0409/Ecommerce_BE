package com.example.Hello.Mapper;


import com.example.Hello.dto.ChatbotInteractionDTO;
import com.example.Hello.entity.ChatbotInteraction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatbotInteractionMapper {
    @Mapping(target = "userId", source = "user.userId")
    ChatbotInteractionDTO toDTO(ChatbotInteraction chatbotInteraction);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ChatbotInteraction toEntity(ChatbotInteractionDTO chatbotInteractionDTO);
}
