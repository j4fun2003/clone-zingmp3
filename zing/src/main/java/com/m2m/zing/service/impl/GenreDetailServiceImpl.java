package com.m2m.zing.service.impl;

import com.m2m.zing.model.GenreDetail;
import com.m2m.zing.model.idClass.GenreDetailId;
import com.m2m.zing.repository.GenreDetailRepository;
import com.m2m.zing.service.GenreDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreDetailServiceImpl implements GenreDetailService {

    @Autowired
    GenreDetailRepository  genreDetailRepository;

    @Override
    public GenreDetail createGenreDetail(GenreDetail genreDetail) throws Exception {
        return genreDetailRepository.save(genreDetail);
    }

    @Override
    public GenreDetail getGenreDetailById(GenreDetailId genreDetailId) throws Exception {
        Optional<GenreDetail> optionalGenreDetail = genreDetailRepository.findById(genreDetailId);
        return optionalGenreDetail.orElse(null);
    }

    @Override
    public GenreDetail updateGenreDetail(GenreDetailId genreDetailId, GenreDetail genreDetail) throws Exception {
        Optional<GenreDetail> optionalGenreDetail = genreDetailRepository.findById(genreDetailId);
        if (optionalGenreDetail.isPresent()) {
            // Set new values to the existing genre detail
            GenreDetail existingGenreDetail = optionalGenreDetail.get();
            // Save the updated genre detail
            return genreDetailRepository.save(existingGenreDetail);
        }
        return null; // Genre detail not found
    }

    @Override
    public GenreDetail deleteGenre(GenreDetailId genreDetailId) throws Exception {
        Optional<GenreDetail> optionalGenreDetail = genreDetailRepository.findById(genreDetailId);
        if (optionalGenreDetail.isPresent()) {
            GenreDetail genreDetail = optionalGenreDetail.get();
            genreDetailRepository.delete(genreDetail);
            return genreDetail;
        }
        return null; // Genre detail not found
    }
}
