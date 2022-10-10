package com.gucardev.trakyabilmuhbe.model;

import com.gucardev.trakyabilmuhbe.model.BaseEntity;
import com.gucardev.trakyabilmuhbe.model.Channel;
import com.gucardev.trakyabilmuhbe.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseEntity {

    @ManyToOne
    private User user;

    @Length(max = 2500)
    private String content;
    private String link;

    @OneToOne
    private Channel channel;

}
