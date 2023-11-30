package com.m2m.zing.repository;

import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAuthor(User user);
}
