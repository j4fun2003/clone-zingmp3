
package com.m2m.zing.api;

import com.m2m.zing.model.Song;
import com.m2m.zing.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song createdSong = songService.createSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
    }

    @GetMapping("/getAllSong")
    public ResponseEntity<?> doGetAllSong(){
        Map<String, Object> result = new HashMap<>();
        try{
            List<Song> data = songService.getAllSong();
            result.put("status", true);
            result.put("message", "Call api getAllSong - success ");
            result.put("data", data);
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Call api getAllSong - fail ");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{songId}")
    public ResponseEntity<?> getSongById(@PathVariable Long songId) {
//        try {
//            Song song = songService.getSongById(songId);
//            System.out.println(song.getId());
//            if (song != null) {
//                return ResponseEntity.ok(song);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch(Exception e){
//            System.out.println(e);
//            return ResponseEntity.notFound().build();
//        }
        Map<String, Object> result = new HashMap<>();
        try{
            Song song = songService.getSongById(songId);
            if(song != null) {
                result.put("status", true);
                result.put("message", "Call api getAllSong - success ");
                result.put("sql_message", "exist record");
                result.put("data", song);
            } else {
                result.put("status", true);
                result.put("message", "Call api getAllSong - success ");
                result.put("sql_message", "do not exist record");
                result.put("data", null);
            }
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Call api getAllSong - fail");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Long songId, @RequestBody Song songDetails) {
        Song updatedSong = songService.updateSong(songId, songDetails);
        if (updatedSong != null) {
            return ResponseEntity.ok(updatedSong);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }
}
