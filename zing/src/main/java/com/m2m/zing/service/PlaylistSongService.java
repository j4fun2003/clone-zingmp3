package com.m2m.zing.service;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.User;
import com.m2m.zing.model.idClass.PlaylistSongId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaylistSongService {
    PlaylistSong getPlaylistSongById(PlaylistSongId playlistSongId);
    List<PlaylistSong> getPlaylistSongByPlaylist(Playlist playlist);

    PlaylistSong createPlaylistSong(PlaylistSong playlistSong) throws Exception;
    PlaylistSong getPlaylistSongById(Long playlistSongId) throws Exception;
    PlaylistSong updatePlaylistSong(Long userId, User userDetails) throws Exception;
}
