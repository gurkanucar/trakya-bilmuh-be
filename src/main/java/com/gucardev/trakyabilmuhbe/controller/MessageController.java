package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.MessageDto;
import com.gucardev.trakyabilmuhbe.model.notification.Message;
import com.gucardev.trakyabilmuhbe.model.notification.MessageType;
import com.gucardev.trakyabilmuhbe.request.MessageRequest;
import com.gucardev.trakyabilmuhbe.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam(required = false) MessageType type) {
        return ResponseEntity.ok(messageService.getMessages(type).stream()
                .map(x -> modelMapper.map(x, MessageDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody MessageRequest messageRequest) {
        var message = modelMapper.map(messageRequest,Message.class);
        return ResponseEntity.ok(modelMapper.map(messageService.create(message), MessageDto.class));
    }

    @PutMapping
    public ResponseEntity<MessageDto> update(@RequestBody MessageRequest messageRequest) {
        var message = modelMapper.map(messageRequest,Message.class);
        return ResponseEntity.ok(modelMapper.map(messageService.update(message), MessageDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.ok().build();
    }


}
