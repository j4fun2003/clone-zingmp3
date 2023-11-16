package com.m2m.zing.repository;

import com.m2m.zing.model.Favorite;
import com.m2m.zing.model.idClass.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}
