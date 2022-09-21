package com.gucardev.trakyabilmuhbe.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnnouncementRequest {
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String content;
    @NotNull
    @NotEmpty
    private String link;
}
