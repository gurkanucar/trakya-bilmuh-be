package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.Role;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("user not found!"));
        existing.setExpertises(user.getExpertises());
        existing.setInterests(user.getInterests());
        existing.setTitle(user.getTitle());
        existing.setName(user.getName());
        existing.setUsername(user.getUsername());
        return userRepository.save(user);
    }

    public Optional<User> findUserByID(Long id) {
        return userRepository.findById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found!"));
    }

    public User findUserByMail(String mail) {
        return userRepository.findUserByMail(mail)
                .orElseThrow(() -> new RuntimeException("user not found!"));
    }


    public User setApproved(String username, boolean approved) {
        User existing = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found!"));
        existing.setApproved(approved);
        return userRepository.save(existing);
    }

    public User updateUserRole(String username, Role role) {
        User existing = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found!"));
        existing.setRole(role);
        return userRepository.save(existing);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public List<User> getApprovedUsers() {
        return userRepository.findAllByApprovedTrue();
    }


}
