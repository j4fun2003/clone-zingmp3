package com.m2m.zing.repository;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByAuthor(User user);
}