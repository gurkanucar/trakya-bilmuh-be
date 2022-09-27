package com.gucardev.trakyabilmuhbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TokenResponseDto {
    private String accessToken;
    private UserDto user;
}
