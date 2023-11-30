
package com.m2m.zing.service;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.User;

import java.util.List;

public interface PlaylistService {
    Playlist createPlaylist(User user, String playlistName) throws Exception;
    List<Playlist> getPlaylistsByUser(User user) throws Exception;
    Playlist getById(Long playlistId) throws Exception;
}