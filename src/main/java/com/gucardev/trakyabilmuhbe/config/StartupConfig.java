package com.gucardev.trakyabilmuhbe.config;

import com.gucardev.trakyabilmuhbe.model.Role;
import com.gucardev.trakyabilmuhbe.model.User;
import com.gucardev.trakyabilmuhbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        log.debug("user is creating!");
        userService.create(User.builder().name("Gurkan").role(Role.ADMIN).password("pass").mail("gurkanucar@trakya.edu.tr").username("gurkan").build());
    }
}
