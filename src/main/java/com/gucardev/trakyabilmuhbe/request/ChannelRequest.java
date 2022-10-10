package com.gucardev.trakyabilmuhbe.request;

import com.gucardev.trakyabilmuhbe.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelRequest {

    private Long id;
    private User user;
    private String channelName;
    private String channelImageUrl;
    private boolean canSendOthers;

}
