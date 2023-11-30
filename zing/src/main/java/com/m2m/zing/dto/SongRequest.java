package com.m2m.zing.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SongRequest {
    String title;
    String description;
    String image;
    LocalDateTime duration;
    Long download;
    String url;
    Long quantity;
}

