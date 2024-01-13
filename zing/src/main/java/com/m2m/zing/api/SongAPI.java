package com.m2m.zing.api;

import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.dto.SongDTO;
import com.m2m.zing.dto.SongRequest;
import com.m2m.zing.model.Album;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.dto.SongRequest;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.SingerService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongAPI {

    @Autowired
    private SongService songService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;

    @Autowired
    SingerService singerService;

    @Autowired
    AlbumService albumService;

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
    public ResponseEntity<?> createSong(@RequestBody SongRequest songRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song song = new Song();
//            set information from data
            song.setTitle(songRequest.getTitle());
            song.setDescription(songRequest.getDescription());
            song.setImage(songRequest.getImage());
            song.setDuration(songRequest.getDuration());
            song.setUrl(songRequest.getUrl());
            song.setNation(songRequest.getNation());
//            default value
            song.setCreateDate(LocalDateTime.now());
            song.setAuthor((User) httpSession.getAttribute(ModelAttributes.CURRENT_USER));
            song.setDownload((long) 0);
            song.setSinger(singerService.getSingerById(songRequest.getSingerId()));
//          create song
            Song createdSong = songService.createSong(song);
//            set result
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

    @GetMapping("/byAuthor/{id}")
    public ResponseEntity<?> getSongsByAuthor(@PathVariable("id") Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Song> songs = songService.getSongsByAuthor_UserId(userId);
            System.out.println(songs);
            if (songs != null) {
    @GetMapping("/byAuthor")
    public ResponseEntity<?> getSongsByAuthor(@RequestParam Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User author = userService.getUserById(userId);
            if (author != null) {
                List<Song> songs = songService.getSongByAuthor(author);
                result.put("status", "success");
                result.put("data", songs);
            } else {
                result.put("status", "failed");
                result.put("detail", "not found author with id : " + userId);
            }

        } catch (Exception e) {
            result.put("status", "error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update-album/{songId}")
    public ResponseEntity<?> addToAlbum(@PathVariable Long songId, @RequestParam Long albumId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Song song = songService.getSongById(songId);
            if (song != null) {
                Album album = albumService.getAlbumById(albumId);
                song.setAlbum(album);
                songService.updateSong(songId,song);
                result.put("status", "success");
            } else {
                result.put("status", "failed");
                result.put("detail", "not found song with id : " + songId);
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/songs/updateAlbumId")
    public ResponseEntity<?> doUpdateSongsAlbumId(@RequestBody SongDTO songDTO) {
        songService.updateSongsAlbumId(songDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        return ResponseEntity.ok(result);
    }

}