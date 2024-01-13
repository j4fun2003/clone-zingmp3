package com.m2m.zing.service.impl;

import com.m2m.zing.model.Favorite;
import com.m2m.zing.model.User;
import com.m2m.zing.model.idClass.FavoriteId;
import com.m2m.zing.repository.FavoriteRepository;
import com.m2m.zing.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> getAll() throws Exception {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getByFavoriteId(FavoriteId favoriteId) throws Exception {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(favoriteId);
        return optionalFavorite.orElse(null);
    }

    @Override
    public Favorite updateFavorite(Favorite favorite) throws Exception {
        // Perform validation or any necessary logic before updating
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite deleteFavorite(Favorite favorite) throws Exception {
        favoriteRepository.delete(favorite);
        return favorite;
    }

    @Override
    public List<Favorite> getByUser(User user) throws Exception {
        return favoriteRepository.findByUser(user);
    }
}
