package com.m2m.zing.service.impl;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.AlbumRepository;
import com.m2m.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public Album createAlbum(Album album) throws Exception {
        return albumRepository.save(album);
    }

    @Override
    public Album getAlbumById(Long albumId) throws Exception {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        return optionalAlbum.orElse(null);
    }

    @Override
    public Album updateAlbum(Long albumId, Album albumDetails) throws Exception {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isPresent()) {
            albumDetails.setAlbumId(albumId);
            return albumRepository.save(albumDetails);
        }
        return null; // Album not found
    }

    @Override
    public Album deleteAlbum(Long albumId) throws Exception {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();
            albumRepository.delete(album);
            return album;
        }
        return null; // Album not found
    }

    @Override
    public List<Album> getAlbumsByUser(User user) throws Exception {
        return albumRepository.findByAuthor(user);
    }

    @Override
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @Override
    public Integer countSongByAlbumId(Long album) {
        return albumRepository.countSongByAlbumId(album);
    }
}
