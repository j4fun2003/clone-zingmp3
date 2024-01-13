package com.m2m.zing.api;
import com.m2m.zing.dto.AlbumRequest;
import com.m2m.zing.model.Album;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.service.SingerService;
import com.m2m.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/albums")
public class AlbumAPI {

    @Autowired
    private AlbumService albumService;
    @Autowired
    SingerService singerService;
    @Autowired
    SongService songService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createAlbum (@RequestBody AlbumRequest albumRequest){
        Map<String, Object> result = new HashMap<>();
        try {
            Album album = new Album();
            album.setImage(albumRequest.getImage());
            album.setTitle(albumRequest.getName());
            album.setSinger(singerService.getSingerById(albumRequest.getSingerId()));
            Album albumCreated =  albumService.createAlbum(album);
            for (Long songId : albumRequest.getSongIds()) {
                Song song =songService.getSongById(songId);
                song.setAlbum(albumCreated);
                songService.updateSong(songId, song);
            }
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Map<String, Object>> getAlbumById(@PathVariable Long albumId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Album fetchedAlbum = albumService.getAlbumById(albumId);
            if (fetchedAlbum != null) {
                result.put("status", "Success");
                result.put("data", fetchedAlbum);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Album not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<Map<String, Object>> updateAlbum(@PathVariable Long albumId, @RequestBody Album albumDetails) {
        Map<String, Object> result = new HashMap<>();
        try {
            Album updatedAlbum = albumService.updateAlbum(albumId, albumDetails);
            if (updatedAlbum != null) {
                result.put("status", "Success");
                result.put("data", updatedAlbum);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Album not found");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Map<String, Object>> deleteAlbum(@PathVariable Long albumId) {
        Map<String, Object> result = new HashMap<>();
        try {
            albumService.deleteAlbum(albumId);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getAlbumsByUser(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = new User();
            user.setUserId(userId);
            List<Album> userAlbums = albumService.getAlbumsByUser(user);
            result.put("status", "Success");
            result.put("data", userAlbums);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
