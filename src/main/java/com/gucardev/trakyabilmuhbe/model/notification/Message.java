package com.gucardev.trakyabilmuhbe.model.notification;

import com.gucardev.trakyabilmuhbe.model.BaseEntity;
import com.gucardev.trakyabilmuhbe.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    @Length(max = 2500)
    private String content;
    private String link;

}
