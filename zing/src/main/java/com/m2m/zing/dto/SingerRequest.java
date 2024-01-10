package com.m2m.zing.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SingerRequest {
    String singerFullName;
    Date singerBirthDay;
    String singerDescription;
    String singerEmail;
    String singerImage;
}
