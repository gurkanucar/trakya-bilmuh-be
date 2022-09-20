package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.AnnouncementDto;
import com.gucardev.trakyabilmuhbe.dto.MessageDto;
import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AnnouncementDto> create(@RequestBody Announcement announcement) {
        return ResponseEntity.ok(modelMapper.map(announcementService.create(announcement), AnnouncementDto.class));
    }

    @PutMapping
    public ResponseEntity<AnnouncementDto> update(@RequestBody Announcement announcement) {
        return ResponseEntity.ok(modelMapper.map(announcementService.update(announcement), AnnouncementDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.ok().build();
    }

}
