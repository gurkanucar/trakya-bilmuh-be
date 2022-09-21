package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnouncementDto {
    private Long id;
    private String content;
    private String link;
    private String title;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
