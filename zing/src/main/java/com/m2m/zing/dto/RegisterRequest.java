package com.m2m.zing.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private String avatar;
    private String fullName;
    private String provider;
    private boolean genders;

}
