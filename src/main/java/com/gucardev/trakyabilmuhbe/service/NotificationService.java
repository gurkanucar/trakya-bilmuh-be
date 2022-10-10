package com.gucardev.trakyabilmuhbe.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final FirebaseMessaging firebaseMessaging;


    public void sendNotification(NotificationMessage notificationMessage) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getContent())
                .build();

        Message message = Message
                .builder()
                .setNotification(notification)
                .setTopic(notificationMessage.getTopic())
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("notification sent!");
    }

}
