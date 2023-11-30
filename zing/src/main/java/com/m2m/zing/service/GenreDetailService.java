package com.m2m.zing.service;

import com.m2m.zing.model.Genre;
import com.m2m.zing.model.GenreDetail;
import com.m2m.zing.model.idClass.GenreDetailId;
import org.springframework.stereotype.Service;

@Service
public interface GenreDetailService {
    GenreDetail createGenreDetail(GenreDetail genreDetail) throws Exception;
    GenreDetail getGenreDetailById(GenreDetailId genreDetailId) throws Exception;
    GenreDetail updateGenreDetail(GenreDetailId genreDetailId, GenreDetail genreDetail) throws Exception;

    GenreDetail deleteGenre(GenreDetailId genreDetailId) throws Exception;
}
