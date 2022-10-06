package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        existing.setLink(message.getLink());
        existing.setMessageType(message.getMessageType());
        return messageRepository.save(existing);
    }

    public Message getById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found!"));
    }

    public void delete(Long id) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found!"));
        messageRepository.delete(existing);
    }

    public List<Message> getMessages(MessageType type) {
        if (type == null) {
            return messageRepository.findAll();
        }
        return messageRepository.findAllByMessageType(type);
    }
}
