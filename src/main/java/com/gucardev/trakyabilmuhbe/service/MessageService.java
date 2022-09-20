package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public Message create(Message message) {
        var user = userService.findUserByID(message.getUser().getId());
        if (user.isEmpty()) {
            throw new RuntimeException("user not found!");
        } else if (!user.get().isApproved()) {
            throw new RuntimeException("user not approved! Please contact with admin");
        }
        return messageRepository.save(message);
    }

    public Message update(Message message) {
        Message existing = messageRepository.findById(message.getId())
                .orElseThrow(() -> new RuntimeException("message not found!"));
        existing.setContent(message.getContent());
        return messageRepository.save(existing);
    }

    public void delete(Long id) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found!"));
        messageRepository.delete(existing);
    }

}
