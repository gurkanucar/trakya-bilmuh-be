package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Announcement;
import com.gucardev.trakyabilmuhbe.model.NotificationMessage;
import com.gucardev.trakyabilmuhbe.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    @Value("${announcementDefaultTopic}")
    String topic;

    private final AnnouncementRepository announcementRepository;


    public Announcement create(Announcement announcement) {
        announcement.setTopic(topic);
        return announcementRepository.save(announcement);
    }

    public Announcement update(Announcement announcement) {
        Announcement existing = getById(announcement.getId());
        existing.setContent(announcement.getContent());
        existing.setLink(announcement.getLink());
        existing.setTitle(announcement.getTitle());
        existing.setUpdatedDateTime(LocalDateTime.now());
        return announcementRepository.save(existing);
    }

    public void delete(Long id) {
        Announcement existing = getById(id);
        announcementRepository.delete(existing);
    }

    public List<Announcement> getAll() {
        return announcementRepository.findAll();
    }

    public Announcement getById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found!"));
    }
}
