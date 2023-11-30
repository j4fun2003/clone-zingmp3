package com.m2m.zing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String email;
    private String avatar;
    private String fullName;
    private LocalDateTime createDate;
    private String provider;
    private boolean active;
    private String role;
    private boolean genders;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Album> albums;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Song> songs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> histories;
}
