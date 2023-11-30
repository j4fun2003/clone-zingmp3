package com.m2m.zing.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String email;
    String password;
    Boolean rememberMe;
}
