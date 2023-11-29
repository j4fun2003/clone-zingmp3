package com.m2m.zing.service;

import com.m2m.zing.model.Genre;

public interface GenreService {
    Genre createGenre(Genre genre);
    Genre getGenreById(Long genreId);
    Genre updateGenre(Long genreId, Genre genreDetails);
    void deleteGenre(Long genreId);
    // Other methods as needed
}
