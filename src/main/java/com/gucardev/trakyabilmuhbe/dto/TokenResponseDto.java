package com.gucardev.trakyabilmuhbe.dto;

import lombok.Data;

@Data
public class TokenResponseDto {
    private String accessToken;
    private UserDto userDto;
}
