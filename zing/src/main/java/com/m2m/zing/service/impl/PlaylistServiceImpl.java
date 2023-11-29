package com.m2m.zing.service.impl;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.PlaylistRepository;
import com.m2m.zing.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createPlaylist(User user, String playlistName) {
        Playlist playlist = new Playlist();
        playlist.setUser(user);
        playlist.setPlaylistName(playlistName);
        playlist.setCreateDate(LocalDateTime.now());

        return playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getPlaylistsByUser(User user) {
        return playlistRepository.findAllByUser(user);
    }

    // Additional methods or functionalities
}