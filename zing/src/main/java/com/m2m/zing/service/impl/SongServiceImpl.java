package com.m2m.zing.service.impl;

import com.m2m.zing.model.Song;
import com.m2m.zing.repository.SongRepository;
import com.m2m.zing.service.SongService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllSong(){
        return songRepository.findAll();
    };
    @Override
    public Song createSong(Song song) {
        // Perform any validation or business logic before saving
        return songRepository.save(song);
    }

    @Override
    public Song getSongById(Long songId) throws Exception {
        System.out.println(">>> songId [SV] = "+ songId);
        return songRepository.findById(songId).orElse(null);
    }

    @Override
    public Song updateSong(Long songId, Song songDetails) {
        Song existingSong = songRepository.findById(songId).orElse(null);
        if (existingSong != null) {
            // Update song details
            existingSong.setTitle(songDetails.getTitle());
            existingSong.setDescription(songDetails.getDescription());
            existingSong.setImage(songDetails.getImage());
            // Set other fields as needed

            return songRepository.save(existingSong);
        }
        return null;
    }

    @Override
    public void deleteSong(Long songId) {
        songRepository.deleteById(songId);
    }
}
