package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.dto.TokenResponseDto;
import com.gucardev.trakyabilmuhbe.dto.UserDto;
import com.gucardev.trakyabilmuhbe.exception.UsernameOrPasswordInvalidException;
import com.gucardev.trakyabilmuhbe.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new UsernameOrPasswordInvalidException("Invalid username or password!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setUserDto(modelMapper
                .map(userService
                        .findUserByUsername(userDetails.getUsername()), UserDto.class));
        responseDto.setAccessToken(tokenService.generateToken(userDetails));
        return responseDto;
    }


}
