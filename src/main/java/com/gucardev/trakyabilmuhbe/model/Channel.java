package com.gucardev.trakyabilmuhbe.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Channel extends BaseEntity {

    @ManyToOne
    private User user;

    private String channelName;
    private String channelTopic;
    private String channelImageUrl;


}