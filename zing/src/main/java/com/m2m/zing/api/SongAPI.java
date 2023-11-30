package com.m2m.zing.api;

import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongAPI {

    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<?> getAllSongs() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Song> songs = songService.getAllSong();
            result.put("status", "Success");
            result.put("data", songs);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createSong(@RequestBody Song song) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song createdSong = songService.createSong(song);
            result.put("status", "Success");
            result.put("data", createdSong);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{songId}")
    public ResponseEntity<?> getSongById(@PathVariable("songId") Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song song = songService.getSongById(songId);
            if (song != null) {
                result.put("status", "Success");
                result.put("data", song);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Song not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{songId}")
    public ResponseEntity<?> updateSong(@PathVariable("songId") Long songId, @RequestBody Song songDetails) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song updatedSong = songService.updateSong(songId, songDetails);
            if (updatedSong != null) {
                result.put("status", "Success");
                result.put("data", updatedSong);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Song not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<?> deleteSong(@PathVariable("songId") Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song deletedSong = songService.deleteSong(songId);
            if (deletedSong != null) {
                result.put("status", "Success");
            } else {
                result.put("status", "Failed");
                result.put("detail", "Song not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/byAuthor")
    public ResponseEntity<?> getSongsByAuthor(@RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User author = new User(); // Get user object using userId (from service or repository)
            List<Song> songs = songService.getSongByAuthor(author);
            result.put("status", "Success");
            result.put("data", songs);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
