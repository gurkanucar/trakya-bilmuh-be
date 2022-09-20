package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
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

}
