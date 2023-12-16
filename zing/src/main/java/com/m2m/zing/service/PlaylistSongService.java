package com.m2m.zing.service;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.idClass.PlaylistSongId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaylistSongService {
    PlaylistSong getPlaylistSongById(PlaylistSongId playlistSongId);
    List<PlaylistSong> getPlaylistSongByPlaylist(Playlist playlist);
    PlaylistSong createPlaylistSong(PlaylistSong playlistSong) throws Exception;
    PlaylistSong updatePlaylistSong(PlaylistSongId playlistSongId, PlaylistSong playlistSongDetails) throws Exception;
    PlaylistSong deletePlaylistSong(PlaylistSongId playlistSongId) throws Exception;
}
