package com.gucardev.trakyabilmuhbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement extends BaseEntity {

    private String title;
    @Length(max = 2500)
    private String content;
    private String link;

    private String topic;

}
