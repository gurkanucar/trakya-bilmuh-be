package com.gucardev.trakyabilmuhbe.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Announcement extends BaseEntity {

    private String title;
    private String content;
    private String link;

}
