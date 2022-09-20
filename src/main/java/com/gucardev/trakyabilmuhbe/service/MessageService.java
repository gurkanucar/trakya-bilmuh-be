package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;


    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public Message update(Message message) {
        Message existing = messageRepository.findById(message.getId())
                .orElseThrow(() -> new RuntimeException("message not found!"));
        existing.setContent(message.getContent());
        return messageRepository.save(existing);
    }

    public void delete(Message message) {
        Message existing = messageRepository.findById(message.getId())
                .orElseThrow(() -> new RuntimeException("message not found!"));
        messageRepository.delete(existing);
    }

}
