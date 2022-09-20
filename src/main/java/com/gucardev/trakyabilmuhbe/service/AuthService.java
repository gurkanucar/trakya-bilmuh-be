package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.dto.TokenResponseDto;
import com.gucardev.trakyabilmuhbe.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenService tokenService;

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken(tokenService.generateToken(userDetails));
        return responseDto;
    }


}
