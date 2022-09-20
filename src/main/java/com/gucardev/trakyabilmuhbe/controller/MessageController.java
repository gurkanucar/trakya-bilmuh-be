package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.MessageDto;
import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<MessageDto> create(Message message) {
        return ResponseEntity.ok(modelMapper.map(messageService.create(message), MessageDto.class));
    }

    @PutMapping
    public ResponseEntity<MessageDto> update(Message message) {
        return ResponseEntity.ok(modelMapper.map(messageService.update(message), MessageDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }


}
