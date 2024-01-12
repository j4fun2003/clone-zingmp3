
package com.m2m.zing.api;

import com.google.gson.stream.JsonToken;
import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.model.Favorite;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.model.idClass.FavoriteId;
import com.m2m.zing.service.FavoriteService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteAPI {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFavorites() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Favorite> favorites = favoriteService.getAll();
            result.put("status", "Success");
            result.put("data", favorites);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}/{songId}")
    public ResponseEntity<Map<String, Object>> getFavoriteById(@PathVariable Long userId, @PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            FavoriteId favoriteId = new FavoriteId(userId, songId);
            Favorite favorite = favoriteService.getByFavoriteId(favoriteId);
            if (favorite != null) {
                result.put("status", "Success");
                result.put("data", favorite);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Favorite not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateFavorite(@RequestBody Favorite favorite) {
        Map<String, Object> result = new HashMap<>();
        try {
            Favorite updatedFavorite = favoriteService.updateFavorite(favorite);
            result.put("status", "Success");
            result.put("data", updatedFavorite);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Map<String, Object>> deleteFavorite(@PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            FavoriteId favoriteId = new FavoriteId();
            favoriteId.setSong(songId);
            favoriteId.setUser(user.getUserId());
            Favorite favorite = favoriteService.getByFavoriteId(favoriteId);
            if (favorite != null) {
                favoriteService.deleteFavorite(favorite);
                result.put("status", "Success");
            }else{
                result.put("status", "Failed");
                result.put("detail","not found favorites with this song and user");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{songId}")
    public ResponseEntity<Map<String, Object>> createFavorite(@PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song song = songService.getSongById(songId);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Favorite favorite = new Favorite();
            favorite.setSong(song);
            favorite.setUser(user);
            favorite.setLikeDate(LocalDateTime.now());
            favoriteService.updateFavorite(favorite);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
