package com.m2m.zing.model;

import com.m2m.zing.model.idClass.PlaylistSongId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist_songs")
@IdClass(PlaylistSongId.class)
public class PlaylistSong {
    @Id
    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    private int sequence;
}
