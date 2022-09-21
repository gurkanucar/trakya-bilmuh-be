package com.gucardev.trakyabilmuhbe.dto;

import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import lombok.Data;

@Data
public class MessageDto {
    private UserDto user;
    private MessageType messageType;
    private String content;
    private String link;
}
