package com.m2m.zing.repository;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByAuthor(User user);

//    @Query("SELECT COUNT(s.songId) FROM Album a JOIN Song s WHERE a.albumId = :albumId")
@Query("    SELECT COUNT(s.songId) FROM Album a JOIN Song s ON a.albumId = s.album.albumId WHERE a.albumId = :albumId")
    Integer countSongByAlbumId(@Param("albumId") Long albumId);


}