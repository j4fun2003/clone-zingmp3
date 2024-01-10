package com.m2m.zing.dto;

import com.m2m.zing.model.Singer;
import com.m2m.zing.model.Song;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
public class SongRequest {
    String title;
    String url;
    String description;
    String image;
    Time duration;
    String nation;
    Integer singerId;
}

