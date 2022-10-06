package com.gucardev.trakyabilmuhbe.aspect;

import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MessageServiceAspect {


    private final NotificationService notificationService;

    @After(value = "execution(* com.gucardev.trakyabilmuhbe.service.MessageService.create(..)) && args(message)")
    public void afterSaveAnnouncement(JoinPoint joinPoint, Message message) {
        log.info("message saved: " + message.getId().toString());
        notificationService.sendNotification(NotificationMessage.builder()
                .title(message.getMessageType().toString())
                .content(message.getContent()).build());
    }


}
