package com.gucardev.trakyabilmuhbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class TrakyaBilmuhBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrakyaBilmuhBeApplication.class, args);
    }

}
