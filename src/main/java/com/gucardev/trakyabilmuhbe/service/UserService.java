package com.gucardev.trakyabilmuhbe.service;

import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user) {
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

    public User setApproved(User user, boolean approved) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("user not found!"));
        existing.setApproved(approved);
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public List<User> getApprovedUsers() {
        return userRepository.findAllByApprovedTrue();
    }


}
