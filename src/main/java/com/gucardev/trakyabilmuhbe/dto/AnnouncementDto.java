package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnnouncementDto {
    private String content;
    private String link;
    private String title;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
