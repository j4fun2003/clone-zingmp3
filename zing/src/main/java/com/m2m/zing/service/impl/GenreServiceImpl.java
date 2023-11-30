package com.m2m.zing.service.impl;

import com.m2m.zing.model.Genre;
import com.m2m.zing.repository.GenreRepository;
import com.m2m.zing.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

   @Autowired
   GenreRepository  genreRepository;

    @Override
    public Genre createGenre(Genre genre) throws Exception {
        return genreRepository.save(genre);
    }

    @Override
    public Genre getGenreById(Long genreId) throws Exception {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        return optionalGenre.orElse(null);
    }

    @Override
    public Genre updateGenre(Long genreId, Genre genreDetails) throws Exception {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        if (optionalGenre.isPresent()) {
            Genre existingGenre = optionalGenre.get();
            // Update specific fields of the existing genre
            genreDetails.setGenreId(genreId);
            // Save the updated genre
            return genreRepository.save(existingGenre);
        }
        return null; // Genre not found
    }

    @Override
    public void deleteGenre(Long genreId) throws Exception {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        optionalGenre.ifPresent(genreRepository::delete);
    }
}
