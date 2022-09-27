package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.dto.TokenResponseDto;
import com.gucardev.trakyabilmuhbe.dto.UserDto;
import com.gucardev.trakyabilmuhbe.exception.GeneralException;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponseDto
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .user(modelMapper.map(userService.findUserByUsername(loginRequest.getUsername()), UserDto.class))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw new GeneralException("Invalid Username or Password", HttpStatus.BAD_REQUEST);
        }

    }

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(username);
    }

}
