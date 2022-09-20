package com.gucardev.trakyabilmuhbe.model.notification;

import com.gucardev.trakyabilmuhbe.model.BaseEntity;
import com.gucardev.trakyabilmuhbe.model.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Message extends BaseEntity {

    @ManyToOne
    private User user;

    private String content;

}
