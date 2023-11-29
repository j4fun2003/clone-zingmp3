package com.m2m.zing.service;

import com.m2m.zing.model.Album;

public interface AlbumService {
    Album createAlbum(Album album);
    Album getAlbumById(Long albumId);
    Album updateAlbum(Long albumId, Album albumDetails);
    void deleteAlbum(Long albumId);
    // Other methods as needed
}
