package com.m2m.zing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String playlistName;
    private LocalDateTime createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
    private List<PlaylistSong> playlistSongs;
}
