package com.gucardev.trakyabilmuhbe.dto;

import com.gucardev.trakyabilmuhbe.model.Channel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private UserDto user;
    private Channel channel;
    private String content;
    private String link;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
