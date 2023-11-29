package com.m2m.zing.service.impl;

import com.m2m.zing.model.Album;
import com.m2m.zing.repository.AlbumRepository;
import com.m2m.zing.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;


    @Override
    public Album createAlbum(Album album) {
        // Perform any validation or business logic before saving
        return albumRepository.save(album);
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId).orElse(null);
    }

    @Override
    public Album updateAlbum(Long albumId, Album albumDetails) {
        Album existingAlbum = albumRepository.findById(albumId).orElse(null);
        if (existingAlbum != null) {
            // Update album details
            existingAlbum.setImage(albumDetails.getImage());
            existingAlbum.setTitle(albumDetails.getTitle());
            // Set other fields as needed

            return albumRepository.save(existingAlbum);
        }
        return null;
    }

    @Override
    public void deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
    }
}
