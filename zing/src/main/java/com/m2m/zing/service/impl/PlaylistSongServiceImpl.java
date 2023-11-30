package com.m2m.zing.service.impl;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.idClass.PlaylistSongId;
import com.m2m.zing.repository.PlayListSongRepository;
import com.m2m.zing.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistSongServiceImpl implements PlaylistSongService {

    @Autowired
    private PlayListSongRepository playlistSongRepository;

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
        return playlistSongRepository.save(playlistSong);
    }

    @Override
    public PlaylistSong updatePlaylistSong(PlaylistSongId playlistSongId, PlaylistSong playlistSongDetails) throws Exception {
        PlaylistSong existingPlaylistSong = playlistSongRepository.findById(playlistSongId).orElse(null);
        if (existingPlaylistSong != null) {
            return playlistSongRepository.save(playlistSongDetails);
        }
        return null; // Handle case where PlaylistSong doesn't exist
    }

    @Override
    public PlaylistSong deletePlaylistSong(PlaylistSongId playlistSongId) throws Exception {
        PlaylistSong existingPlaylistSong = playlistSongRepository.findById(playlistSongId).orElse(null);
        if (existingPlaylistSong != null) {
            playlistSongRepository.delete(existingPlaylistSong);
            return existingPlaylistSong; // Return the deleted PlaylistSong
        }
        return null; // Handle case where PlaylistSong doesn't exist
    }
}
