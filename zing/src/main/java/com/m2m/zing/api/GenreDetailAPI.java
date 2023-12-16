package com.m2m.zing.api;
import com.m2m.zing.model.GenreDetail;
import com.m2m.zing.model.idClass.GenreDetailId;
import com.m2m.zing.service.GenreDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/genredetails")
public class GenreDetailAPI {

    @Autowired
    private GenreDetailService genreDetailService;

    // Endpoint tạo genre detail mới
    @PostMapping
    public ResponseEntity<Map<String, Object>> createGenreDetail(@RequestBody GenreDetail genreDetail) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để tạo genre detail mới
            GenreDetail createdGenreDetail = genreDetailService.createGenreDetail(genreDetail);
            result.put("status", "Success");
            result.put("data", createdGenreDetail);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint lấy genre detail theo ID
    @GetMapping("/{genreId}/{songId}")
    public ResponseEntity<Map<String, Object>> getGenreDetailById(@PathVariable Long genreId, @PathVariable Long songId) {
        GenreDetailId genreDetailId = new GenreDetailId(genreId, songId);
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để lấy genre detail theo ID
            GenreDetail fetchedGenreDetail = genreDetailService.getGenreDetailById(genreDetailId);
            result.put("status", "Success");
            result.put("data", fetchedGenreDetail);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint cập nhật genre detail
    @PutMapping("/{genreId}/{songId}")
    public ResponseEntity<Map<String, Object>> updateGenreDetail(@PathVariable Long genreId, @PathVariable Long songId, @RequestBody GenreDetail genreDetail) {
        GenreDetailId genreDetailId = new GenreDetailId(genreId, songId);
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để cập nhật genre detail
            GenreDetail updatedGenreDetail = genreDetailService.updateGenreDetail(genreDetailId, genreDetail);
            result.put("status", "Success");
            result.put("data", updatedGenreDetail);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint xóa genre detail
    @DeleteMapping("/{genreId}/{songId}")
    public ResponseEntity<Map<String, Object>> deleteGenreDetail(@PathVariable Long genreId, @PathVariable Long songId) {
        GenreDetailId genreDetailId = new GenreDetailId(genreId, songId);
        Map<String, Object> result = new HashMap<>();
        try {
            genreDetailService.deleteGenre(genreDetailId);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
