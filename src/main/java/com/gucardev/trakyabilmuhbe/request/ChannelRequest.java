package com.gucardev.trakyabilmuhbe.request;

import com.gucardev.trakyabilmuhbe.model.User;
import lombok.Data;

@Data
public class ChannelRequest {

    private Long id;
    private User user;
    private String channelName;
    private String channelImageUrl;

}
