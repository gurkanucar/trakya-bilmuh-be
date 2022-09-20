package com.gucardev.trakyabilmuhbe.controller;

import com.gucardev.trakyabilmuhbe.dto.UserDto;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.request.RegisterUserRequest;
import com.gucardev.trakyabilmuhbe.request.UpdateUserRequest;
import com.gucardev.trakyabilmuhbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getApprovedUserList() {
        return ResponseEntity.ok(userService.getApprovedUsers().stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getUsers().stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity register(@RequestBody RegisterUserRequest registerUserRequest) {
        User user = modelMapper.map(registerUserRequest, User.class);
        return ResponseEntity.ok(modelMapper.map(userService.create(user), UserDto.class));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UpdateUserRequest updateUserRequest) {
        User user = modelMapper.map(updateUserRequest, User.class);
        return ResponseEntity.ok(modelMapper.map(userService.update(user), UserDto.class));
    }


}
