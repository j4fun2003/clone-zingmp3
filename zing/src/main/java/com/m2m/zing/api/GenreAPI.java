package com.m2m.zing.api;
import com.m2m.zing.model.Genre;
import com.m2m.zing.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/genres")
public class GenreAPI {

    @Autowired
    private GenreService genreService;

    // Endpoint tạo genre mới
    @PostMapping
    public ResponseEntity<Map<String, Object>> createGenre(@RequestBody String genreTitle) {
        Map<String, Object> result = new HashMap<>();
        try {
            Genre genre = new Genre();
            genre.setTitle(genreTitle);
            Genre createdGenre = genreService.createGenre(genre);
            result.put("status", "Success");
            result.put("data", createdGenre);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint lấy genre theo ID
    @GetMapping("/{genreId}")
    public ResponseEntity<Map<String, Object>> getGenreById(@PathVariable Long genreId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Genre fetchedGenre = genreService.getGenreById(genreId);
            if(fetchedGenre != null){
                result.put("status", "Success");
                result.put("data", fetchedGenre);
            }
            else{
                result.put("status", "Failed");
                result.put("detail","Genre Không Tồn Tại");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
    @PutMapping("/{genreId}")
    public ResponseEntity<Map<String, Object>> updateGenre(@PathVariable Long genreId, @RequestBody String genreTitle) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để cập nhật genre
            Genre genre = genreService.getGenreById(genreId);
            if(genre != null) {
                genre.setTitle(genreTitle);
                Genre updatedGenre = genreService.updateGenre(genreId, genre);
                result.put("status", "Success");
                result.put("data", updatedGenre);
            }else{
                result.put("status", "Failed");
                result.put("detail","Không tồn tại genre");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{genreId}")
    public ResponseEntity<Map<String, Object>> deleteGenre(@PathVariable Long genreId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Genre genre = genreService.getGenreById(genreId);
            if(genre != null) {
                genreService.deleteGenre(genreId);
                result.put("status", "Success");
            }else{
                result.put("status", "Failed");
                result.put("detail","Không tồn tại genre");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
