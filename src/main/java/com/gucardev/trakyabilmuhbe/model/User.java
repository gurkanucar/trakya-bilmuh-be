package com.gucardev.trakyabilmuhbe.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "`user`")
@Data
public class User extends BaseEntity {

    private String username;
    private String password;
    private String name;
    private String surname;
    private String title;
    private String mail;
    private String profileImageUrl;
    private String interests;
    private String expertises;
    private String profileUrl;
    private boolean approved;
    private boolean resetPassword;

    @Enumerated(EnumType.STRING)
    private Role role;


}
