
package com.m2m.zing.api;

import com.m2m.zing.model.Favorite;
import com.m2m.zing.model.idClass.FavoriteId;
import com.m2m.zing.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteAPI {

    @Autowired
    private FavoriteService favoriteService;

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

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteFavorite(@RequestBody Favorite favorite) {
        Map<String, Object> result = new HashMap<>();
        try {
            favoriteService.deleteFavorite(favorite);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
