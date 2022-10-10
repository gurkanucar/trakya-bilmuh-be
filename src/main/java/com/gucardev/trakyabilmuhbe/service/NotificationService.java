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
        String topic = notificationMessage.getTopic();
        if (topic == null || topic.isBlank()) {
            topic = "/topics/all";
        } else {
            topic = "/topics/" + topic;
        }

        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getContent())
                .build();

        Message message = Message
                .builder()
                .setNotification(notification)
                .setTopic(topic)
                //.setToken("fufVQteARgO8ERqHL-9jk7:APA91bHu42_Kh7W2hrkVHrZyMUg5jayKXUEIrzCF7Kx9-ZiAsKoon3PV15jXCziqIo5zx0jgDyEUN4NzrBFjprFGHfcP-1ydVj5uNJra7ykAipf6LqVI07py8suYkRrUtLnxa0gXDXKi")
                //.setTopic("/topics/all")
                .build();

        try {
            firebaseMessaging.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("notification sent!");
    }

}
