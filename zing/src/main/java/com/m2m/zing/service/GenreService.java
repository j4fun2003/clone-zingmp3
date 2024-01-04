package com.m2m.zing.service;

import com.m2m.zing.model.Genre;

import java.util.List;

public interface GenreService {
    Genre createGenre(Genre genre) throws Exception;
    Genre getGenreById(Long genreId) throws Exception;
    Genre updateGenre(Long genreId, Genre genreDetails) throws Exception;
    void deleteGenre(Long genreId) throws Exception;
    List<Genre> getAllGenre() throws Exception;
}
