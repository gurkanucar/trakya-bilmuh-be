package com.gucardev.trakyabilmuhbe.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FirebaseMessaging firebaseMessaging;


    public String sendNotification(NotificationMessage notificationMessage) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getContent())
                .build();

        Message message = Message
                .builder()
                .setNotification(notification)
                .setTopic("/topics/all")
               // .setToken("emvFWFA0QXG1OAtwIckW6W:APA91bF85ziTTBpL2vQGghsuynutlA0oRbv7qLYGP_8K5DyjhnoyW416E9PysoAIRkA83GiBrN3YIYoqc1GKUWaH7BmeL7WaAeCoXsoMAD_u1m7bnHZyxGWms4P14xArTbtOfevSn3Xk")
                .build();

        try {
            return firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
