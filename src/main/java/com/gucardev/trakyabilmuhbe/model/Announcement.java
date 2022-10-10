package com.gucardev.trakyabilmuhbe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announcement extends BaseEntity {

    private String title;
    @Length(max = 2500)
    private String content;
    private String link;

    private String topic;

}
