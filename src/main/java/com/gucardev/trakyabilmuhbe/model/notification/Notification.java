package com.gucardev.trakyabilmuhbe.model.notification;

import com.gucardev.trakyabilmuhbe.model.BaseEntity;
import com.gucardev.trakyabilmuhbe.model.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Notification extends BaseEntity {

    @ManyToOne
    private User user;

    private String content;

}
