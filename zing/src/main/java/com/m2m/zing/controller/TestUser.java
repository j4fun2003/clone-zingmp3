package com.m2m.zing.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestUser {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
