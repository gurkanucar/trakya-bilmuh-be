package com.gucardev.trakyabilmuhbe.request;

import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import lombok.Data;


@Data
public class MessageRequest {
    private User user;
    private MessageType messageType;
    private String content;
    private String link;
}
