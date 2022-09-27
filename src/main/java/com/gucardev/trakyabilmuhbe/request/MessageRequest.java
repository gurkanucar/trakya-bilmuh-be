package com.gucardev.trakyabilmuhbe.request;

import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class MessageRequest {
    @NotNull
    private User user;
    @NotNull
    @NotEmpty
    private MessageType messageType;
    @NotNull
    @NotEmpty
    private String content;
    private String link;
    private Long id;
}
