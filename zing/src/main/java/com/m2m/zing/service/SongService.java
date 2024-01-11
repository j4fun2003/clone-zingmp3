
package com.m2m.zing.service;

import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface SongService {
    List<Song> getAllSong() throws Exception;
    Song createSong(Song song) throws Exception;
    Song getSongById(Long songId) throws Exception;
    Song updateSong(Long songId, Song songDetails) throws Exception;
    Song deleteSong(Long songId) throws Exception;
    List<Song> getSongByAuthor(User user);

    List<Song> getSongsByAuthorId(Long id,Pageable page);

    List<Song> getAllCurrent(Pageable page);

    List<Song> getSongByAlbum(Long id);

    public List<Song> getTop5SongsNewRealease();
}