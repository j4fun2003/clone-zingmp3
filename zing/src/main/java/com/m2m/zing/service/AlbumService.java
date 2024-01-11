package com.m2m.zing.service;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumService {
    Album createAlbum(Album album) throws Exception;
    Album getAlbumById(Long albumId) throws Exception;
    Album updateAlbum(Long albumId, Album albumDetails) throws Exception;
    Album deleteAlbum(Long albumId) throws Exception;
    List<Album> getAlbumsByUser(User user) throws Exception;

    List<Album> getAll();

    Integer countSongByAlbumId(Long album);


}
