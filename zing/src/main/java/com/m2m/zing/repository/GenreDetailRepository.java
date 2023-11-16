package com.m2m.zing.repository;

import com.m2m.zing.model.Genre;
import com.m2m.zing.model.GenreDetail;
import com.m2m.zing.model.idClass.GenreDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDetailRepository extends JpaRepository<GenreDetail, GenreDetailId> {
}
