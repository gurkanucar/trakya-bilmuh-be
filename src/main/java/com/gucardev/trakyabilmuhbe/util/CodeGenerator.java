package com.gucardev.trakyabilmuhbe.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CodeGenerator {

    @Value("${codeLength}")
    private int codeLength;

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder generated = new StringBuilder();

        var letters = "abcdefghijklmnprstuvyzqw123456789"
                .toUpperCase()
                .chars()
                .mapToObj(x -> (char) x)
                .collect(Collectors.toList());

        Collections.shuffle(letters);

        for (int i = 0; i < codeLength; i++) {
            generated.append(letters.get(random.nextInt(letters.size())));
        }
        return generated.toString();
    }

}
