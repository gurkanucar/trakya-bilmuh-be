package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.ChannelDto;
import com.gucardev.trakyabilmuhbe.request.ChannelRequest;
import com.gucardev.trakyabilmuhbe.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<ChannelDto>> getChannelList() {
        return ResponseEntity.ok(channelService.getAllChannels()
                .stream()
                .map(x -> modelMapper.map(x, ChannelDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/my-channels")
    public ResponseEntity<List<ChannelDto>> getMyChannels() {
        return ResponseEntity.ok(channelService.getMyChannels()
                .stream()
                .map(x -> modelMapper.map(x, ChannelDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ChannelDto> createChannel(ChannelRequest channelRequest) {
        return ResponseEntity.ok(modelMapper.map(channelService.create(channelRequest), ChannelDto.class));
    }

    @PutMapping
    public ResponseEntity<ChannelDto> updateChannel(ChannelRequest channelRequest) {
        return ResponseEntity.ok(modelMapper.map(channelService.update(channelRequest), ChannelDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable Long id) {
        channelService.delete(id);
        return ResponseEntity.ok().build();
    }

}
