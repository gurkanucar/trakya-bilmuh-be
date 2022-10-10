package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

@Data
public class ChannelDto {

    private Long id;
    private UserDto user;
    private String channelName;
    private String channelTopic;
    private String channelImageUrl;
    private boolean canSendOthers;

}
