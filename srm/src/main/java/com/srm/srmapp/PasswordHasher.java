package com.srm.srmapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";
        String hashed = encoder.encode(rawPassword);

        System.out.println("Хеш: " + hashed);
    }
}
