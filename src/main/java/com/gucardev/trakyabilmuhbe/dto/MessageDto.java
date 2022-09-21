package com.gucardev.trakyabilmuhbe.dto;

import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private UserDto user;
    private MessageType messageType;
    private String content;
    private String link;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
