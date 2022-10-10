package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.exception.PermissionError;
import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.Message;
import com.gucardev.trakyabilmuhbe.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final AuthService authService;
    private final ChannelService channelService;

    public Message create(Message message) {
        var user = userService.findUserByID(message.getUser().getId());
        if (user.isEmpty()) {
            throw new RuntimeException("user not found!");
        } else if (!user.get().isApproved()) {
            throw new RuntimeException("user not approved! Please contact with admin");
        }

        // TODO check for canSendOthers
//        if (!message.getChannel().isCanSendOthers() && !authService.checkForPermission(message.getChannel().getUser().getId())) {
//            throw new PermissionError("Permission not granted!");
//        }

        return messageRepository.save(message);
    }

    public Message update(Message message) {
        Message existing = messageRepository.findById(message.getId())
                .orElseThrow(() -> new RuntimeException("message not found!"));
        if (!authService.checkForPermission(existing.getUser().getId())) {
            throw new PermissionError("Permission not granted!");
        }
        var channel = channelService.getByID(message.getChannel().getId());
        existing.setChannel(channel);
        existing.setContent(message.getContent());
        existing.setLink(message.getLink());
        return messageRepository.save(existing);
    }

    public Message getById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found!"));
    }

    public void delete(Long id) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found!"));
        if (!authService.checkForPermission(existing.getUser().getId())) {
            throw new PermissionError("Permission not granted!");
        }
        messageRepository.delete(existing);
    }

    public List<Message> getMessages(Channel channel) {
        if (channel == null) {
            return messageRepository.findAll();
        }
        return messageRepository.findAllByChannelOrderByCreatedDateTimeDesc(channel);
    }
}
