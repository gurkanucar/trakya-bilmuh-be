package com.gucardev.trakyabilmuhbe.model;

import javax.persistence.Entity;

@Entity
public class Announcement extends BaseEntity {

    private String content;
    private String link;

}
