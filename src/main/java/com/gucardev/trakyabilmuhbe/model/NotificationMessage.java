package com.gucardev.trakyabilmuhbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessage {
    private String content;
    private String title;
    private String topic;
}
