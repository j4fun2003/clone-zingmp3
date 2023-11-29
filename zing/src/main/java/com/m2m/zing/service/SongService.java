
package com.m2m.zing.service;

import com.m2m.zing.model.Song;

import java.sql.SQLException;
import java.util.List;

public interface SongService {
    List<Song> getAllSong();
    Song createSong(Song song);
    Song getSongById(Long songId) throws Exception;
    Song updateSong(Long songId, Song songDetails);
    void deleteSong(Long songId);
    // Other methods as needed
}