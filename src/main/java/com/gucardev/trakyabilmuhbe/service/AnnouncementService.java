package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;


    public Announcement create(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    public Announcement update(Announcement announcement) {
        Announcement existing = announcementRepository.findById(announcement.getId())
                .orElseThrow(() -> new RuntimeException("announcement not found!"));
        existing.setContent(announcement.getContent());
        existing.setLink(announcement.getLink());
        existing.setTitle(announcement.getTitle());
        existing.setUpdatedDateTime(LocalDateTime.now());
        return announcementRepository.save(existing);
    }

    public void delete(Long id) {
        Announcement existing = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("announcement not found!"));
        announcementRepository.delete(existing);
    }

    public List<Announcement> getAll() {
        return announcementRepository.findAll();
    }
}
