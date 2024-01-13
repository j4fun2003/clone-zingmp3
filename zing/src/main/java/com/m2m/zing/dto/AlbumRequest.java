package com.m2m.zing.dto;

import lombok.Data;

import java.util.List;
@Data
public class AlbumRequest {
    String name;
    String image;
    Integer singerId;
    List<Long> SongIds;
}
