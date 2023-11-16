package com.m2m.zing.repository;

import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.idClass.PlaylistSongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListSongRepository extends JpaRepository<PlaylistSong, PlaylistSongId> {
}
