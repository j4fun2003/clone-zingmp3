package com.m2m.zing.service.impl;

import com.m2m.zing.model.Genre;
import com.m2m.zing.repository.GenreRepository;
import com.m2m.zing.service.GenreService;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public Genre updateGenre(Long genreId, Genre genreDetails) {
        Genre existingGenre = genreRepository.findById(genreId).orElse(null);
        if (existingGenre != null) {
            existingGenre.setTitle(genreDetails.getTitle());
            return genreRepository.save(existingGenre);
        }
        return null;
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}
