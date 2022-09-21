package com.gucardev.trakyabilmuhbe.dto;

import com.gucardev.trakyabilmuhbe.model.Role;
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
    private Role role;
    private String profileUrl;
    private boolean approved;
    private boolean resetPassword;

}
