package com.m2m.zing.service.impl;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.PlaylistRepository;
import com.m2m.zing.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createPlaylist(User user, String playlistName) throws Exception {
        Playlist playlist = new Playlist();
        playlist.setUser(user);
        playlist.setPlaylistName(playlistName);
        return playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getPlaylistsByUser(User user) throws Exception {
        return playlistRepository.findByUser(user);
    }

    @Override
    public Playlist getById(Long playlistId)  throws Exception{
        return playlistRepository.findById(playlistId).orElse(null);
    }
}
