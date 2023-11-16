package com.m2m.zing.model.idClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaylistSongId implements Serializable {
    private Long playlist;
    private Long song;
}