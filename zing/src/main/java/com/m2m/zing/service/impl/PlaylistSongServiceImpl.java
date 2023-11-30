package com.m2m.zing.service.impl;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.User;
import com.m2m.zing.model.idClass.PlaylistSongId;
import com.m2m.zing.repository.PlayListSongRepository;
import com.m2m.zing.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongServiceImpl implements PlaylistSongService {

    @Autowired
    PlayListSongRepository playlistSongRepository;

    @Override
    public PlaylistSong getPlaylistSongById(PlaylistSongId playlistSongId) {
        return playlistSongRepository.findById(playlistSongId).orElse(null);
    }

    @Override
    public List<PlaylistSong> getPlaylistSongByPlaylist(Playlist playlist) {
        return playlistSongRepository.findByPlaylist(playlist);
    }

    @Override
    public PlaylistSong createPlaylistSong(PlaylistSong playlistSong) throws Exception {
        return null;
    }

    @Override
    public PlaylistSong getPlaylistSongById(Long playlistSongId) throws Exception {
        return null;
    }

    @Override
    public PlaylistSong updatePlaylistSong(Long userId, User userDetails) throws Exception {
        return null;
    }
}
