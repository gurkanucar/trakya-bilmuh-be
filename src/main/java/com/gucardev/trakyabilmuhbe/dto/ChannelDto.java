package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChannelDto {

    private Long id;
    private UserDto user;
    private String channelName;
    private String channelTopic;
    private String channelImageUrl;
    private boolean canSendOthers;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
