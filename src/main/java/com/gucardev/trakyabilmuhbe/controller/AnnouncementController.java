package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.AnnouncementDto;
import com.gucardev.trakyabilmuhbe.dto.MessageDto;
import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.request.AnnouncementRequest;
import com.gucardev.trakyabilmuhbe.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(modelMapper.map(announcementService.getById(id), AnnouncementDto.class));
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> getAnnouncements() {
        return ResponseEntity.ok(announcementService.getAll().stream()
                .map(x -> modelMapper.map(x, AnnouncementDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<AnnouncementDto> create(@Valid @RequestBody AnnouncementRequest announcementRequest) {
        var announcement = modelMapper.map(announcementRequest, Announcement.class);
        return ResponseEntity.ok(modelMapper.map(announcementService.create(announcement), AnnouncementDto.class));
    }

    @PutMapping
    public ResponseEntity<AnnouncementDto> update(@Valid @RequestBody AnnouncementRequest announcementRequest) {
        var announcement = modelMapper.map(announcementRequest, Announcement.class);
        return ResponseEntity.ok(modelMapper.map(announcementService.update(announcement), AnnouncementDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.ok().build();
    }

}
