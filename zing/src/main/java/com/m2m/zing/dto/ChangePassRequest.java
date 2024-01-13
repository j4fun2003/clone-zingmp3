package com.m2m.zing.dto;

import lombok.Data;

@Data
public class ChangePassRequest {
    private String password;
    private String newPass;
    private String confirm;

}
