package com.m2m.zing.service.impl;

import com.m2m.zing.model.Singer;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.AlbumRepository;
import com.m2m.zing.repository.SongRepository;
import com.m2m.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    AlbumRepository albumRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> getAllSong() throws Exception {
        return songRepository.findAll();
    }

    @Override
    public Song createSong(Song song) throws Exception {
        return songRepository.save(song);
    }

    @Override
    public Song getSongById(Long songId) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(songId);
        return optionalSong.orElse(null);
    }

    @Override
    public Song updateSong(Long songId, Song songDetails) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            songDetails.setSongId(songId);
            return songRepository.save(songDetails);
        }
        return null; // Song not found
    }

    @Override
    public Song deleteSong(Long songId) throws Exception {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            songRepository.delete(song);
            return song;
        }
        return null; // Song not found
    }

    @Override
    public List<Song> getSongByAuthor(User user) {
        return songRepository.findByAuthor(user);
    }

    @Override
    public List<Song> getSongsByAuthorId(Long id, Pageable page) {
        return songRepository.getSongsByAuthor(id, page);
    }


    @Override
    public List<Song> getAllCurrent(Pageable page) {
        return songRepository.getAllCurrent(page);
    }

    @Override
    public List<Song> getSongByAlbum(Long id) {
        return songRepository.getSongByAlbum_AlbumId(id);
    }

    @Override
    public List<Song> getTop5SongsNewRealease() {
        Pageable pageable = PageRequest.of(0, 5); // First page, limit to 5 results
        return songRepository.findTop5ByOrderByCreateDateDesc(pageable);
    }

    @Override
    public List<Song> getSongBySinger(Singer singer) {
        return songRepository.getSOngBySinger(singer);
    }


}
