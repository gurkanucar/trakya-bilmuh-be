package com.gucardev.trakyabilmuhbe.aspect;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
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
public class AnnouncementServiceAspect {


    private final NotificationService notificationService;

    @After(value = "execution(* com.gucardev.trakyabilmuhbe.service.AnnouncementService.create(..)) && args(announcement)")
    public void afterSaveAnnouncement(JoinPoint joinPoint, Announcement announcement) {
        log.info("announcement saved: " + announcement.getId().toString());
        notificationService.sendNotification(NotificationMessage.builder()
                .title(announcement.getTitle())
                .content(announcement.getContent()).build());
    }

    @After(value = "execution(* com.gucardev.trakyabilmuhbe.service.AnnouncementService.update(..)) && args(announcement)")
    public void afterUpdateAnnouncement(JoinPoint joinPoint, Announcement announcement) {
        log.info("announcement updated: " + announcement.getId().toString());
        notificationService.sendNotification(NotificationMessage.builder()
                .title("UPDATE! " + announcement.getTitle())
                .content(announcement.getContent()).build());
    }

}
