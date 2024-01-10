package com.m2m.zing.repository;

import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAuthor(User user);

    @Query("SELECT s FROM Song s ORDER BY s.createDate DESC")
    List<Song> getAllCurrent(Pageable page);

    @Query("SELECT s FROM Song s WHERE s.author.userId = ?1")
    List<Song> getSongsByAuthor(Long id,Pageable page);

    @Query("SELECT s FROM Song s ORDER BY s.createDate DESC")
    List<Song> findTop5ByOrderByCreateDateDesc(Pageable page);
}
