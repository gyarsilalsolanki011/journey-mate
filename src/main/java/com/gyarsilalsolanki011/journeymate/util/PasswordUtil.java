package com.gyarsilalsolanki011.journeymate.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
    public static String encode(String password) {
        return ENCODER.encode(password);
    }
}
