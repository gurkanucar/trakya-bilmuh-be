package com.gucardev.trakyabilmuhbe.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
import com.gucardev.trakyabilmuhbe.model.Message;
import com.gucardev.trakyabilmuhbe.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MessageServiceAspect {

    private final ModelMapper mapper;
    private final NotificationService notificationService;

//    @After(value = "execution(* com.gucardev.trakyabilmuhbe.service.MessageService.create(..)) && args(message)")
//    public void afterSaveMessage(JoinPoint joinPoint, Message message) {
//        notificationService.sendNotification(NotificationMessage.builder()
//                .title(message.getChannel().getChannelName())
//                .topic(message.getChannel().getChannelTopic())
//                .content(message.getContent()).build());
//    }

    @Around("execution(* com.gucardev.trakyabilmuhbe.service.MessageService.create(..))")
    public Object afterSaveMessage(ProceedingJoinPoint pjp) throws Throwable {
        Object[] arguments = pjp.getArgs();
        var result = mapper.map(pjp.proceed(), Message.class);
        notificationService.sendNotification(NotificationMessage.builder()
                .title(result.getChannel().getChannelName())
                .topic(result.getChannel().getChannelTopic())
                .content(result.getContent()).build());

        return result;
    }

}
