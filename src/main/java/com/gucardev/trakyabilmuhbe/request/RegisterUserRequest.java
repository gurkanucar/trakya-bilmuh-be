package com.gucardev.trakyabilmuhbe.request;

import lombok.Data;

@Data
public class RegisterUserRequest {
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
}
