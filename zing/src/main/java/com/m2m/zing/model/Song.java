package com.m2m.zing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
@ToString(exclude = { "genres", "playlists", "favorites", "histories"})
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;
    private String title;
    private String description;
    private String image;
    private Time duration;
    private Long download;
    private String url;
    private LocalDateTime createDate;
    private Long quantity;
    private String nation;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @JsonIgnore
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<GenreDetail> genreDetails;
    @JsonIgnore
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<PlaylistSong> playlistSongs;
    @JsonIgnore
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Favorite> favorites;
    @JsonIgnore
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<History> histories;
}
