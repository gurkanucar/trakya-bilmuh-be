package com.gucardev.trakyabilmuhbe.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String name;
    private String surname;
    private String title;
    private String mail;
    private String profileImageUrl;
    private String interests;
    private String expertises;
    private String profileUrl;
}
