package com.m2m.zing.dto;

import lombok.Data;

import java.util.List;
@Data
public class SongDTO {
    private List<Long> songIds;
    private Long albumId;

    // Constructors, getters, and setters
}