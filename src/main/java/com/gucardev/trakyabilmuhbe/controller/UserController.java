package com.gucardev.trakyabilmuhbe.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.gucardev.trakyabilmuhbe.dto.UserDto;
import com.gucardev.trakyabilmuhbe.model.Role;
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
    public ResponseEntity<List<UserDto>> getAll()  {
        return ResponseEntity.ok(userService.getUsers().stream()
                .map(x -> modelMapper.map(x, UserDto.class))
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserRequest registerUserRequest) {
        User user = modelMapper.map(registerUserRequest, User.class);
        return ResponseEntity.ok(modelMapper.map(userService.create(user), UserDto.class));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UpdateUserRequest updateUserRequest) {
        User user = modelMapper.map(updateUserRequest, User.class);
        return ResponseEntity.ok(modelMapper.map(userService.update(user), UserDto.class));
    }

    @PatchMapping("/approve/{username}")
    public ResponseEntity changeApprovedStatus(@PathVariable String username, Boolean approved) {
        return ResponseEntity.ok(modelMapper.map(userService.setApproved(username, approved), UserDto.class));
    }

    @PatchMapping("/role/{username}")
    public ResponseEntity updateRole(@PathVariable String username, @RequestBody Role role) {
        return ResponseEntity.ok(modelMapper.map(userService.updateUserRole(username, role), UserDto.class));
    }

}
