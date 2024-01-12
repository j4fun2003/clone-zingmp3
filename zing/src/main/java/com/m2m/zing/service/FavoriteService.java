package com.m2m.zing.service;


import com.m2m.zing.model.Favorite;
import com.m2m.zing.model.User;
import com.m2m.zing.model.idClass.FavoriteId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoriteService {
    List<Favorite> getAll() throws Exception;
    Favorite getByFavoriteId(FavoriteId favoriteId) throws Exception;
    Favorite updateFavorite(Favorite favorite) throws Exception;
    Favorite deleteFavorite(Favorite favorite) throws Exception;
    List<Favorite> getByUser(User user) throws Exception;
}
