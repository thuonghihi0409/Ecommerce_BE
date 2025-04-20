package com.example.Hello.Mapper;


import com.example.Hello.dto.ChatMessageDTO;
import com.example.Hello.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    @Mapping(target = "senderId", source = "sender.userId")
    @Mapping(target = "receiverId", source = "receiver.userId")
    ChatMessageDTO toDTO(ChatMessage chatMessage);

    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    ChatMessage toEntity(ChatMessageDTO chatMessageDTO);
}

