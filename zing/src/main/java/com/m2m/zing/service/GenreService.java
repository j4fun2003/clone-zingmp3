package com.m2m.zing.service;

import com.m2m.zing.model.Genre;

public interface GenreService {
    Genre createGenre(Genre genre) throws Exception;
    Genre getGenreById(Long genreId) throws Exception;
    Genre updateGenre(Long genreId, Genre genreDetails) throws Exception;
    void deleteGenre(Long genreId) throws Exception;
}
