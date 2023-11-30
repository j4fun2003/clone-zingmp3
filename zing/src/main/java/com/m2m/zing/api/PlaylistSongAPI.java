package com.m2m.zing.api;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.PlaylistSong;
import com.m2m.zing.model.idClass.PlaylistSongId;
import com.m2m.zing.service.PlaylistService;
import com.m2m.zing.service.PlaylistSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/playlistsongs")
public class PlaylistSongAPI {

    @Autowired
    private PlaylistSongService playlistSongService;

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/{playlistId}/{songId}")
    public ResponseEntity<?> getPlaylistSongById(@PathVariable Long playlistId, @PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {
            PlaylistSongId playlistSongId = new PlaylistSongId(playlistId, songId);
            PlaylistSong playlistSong = playlistSongService.getPlaylistSongById(playlistSongId);
            if(playlistSong!=null){
                result.put("status","Success");
                result.put("data",playlistSong);
            }else{
                result.put("status","Failed");
                result.put("detail","Playlist song không tồn tại");
            }
            }catch (Exception e){
            result.put("status","Error");
            result.put("detail",e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/playlist/{playlistId}")
    public ResponseEntity<?> getPlaylistSongsByPlaylist(@PathVariable Long playlistId) {

        Map<String, Object> result = new HashMap<>();
        try {
            Playlist playlist = playlistService.getById(playlistId);
            if(playlist!=null){
                List<PlaylistSong> playlistSongs =  playlistSongService.getPlaylistSongByPlaylist(playlist);
                if(!playlistSongs.isEmpty()){
                    result.put("status","Success");
                    result.put("data",playlistSongs);
                }else {
                    result.put("status","Failed");
                    result.put("detail","Không có bất kì bài hát nào trong playlist");
                }
            }else{
                result.put("status","Failed");
                result.put("detail","Playlist không tồn tại");
            }
        }catch (Exception e){
            result.put("status","Error");
            result.put("detail",e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<?> createPlaylistSong(@RequestBody PlaylistSong playlistSong) {
        Map<String, Object> result = new HashMap<>();
        try {
            PlaylistSong createdPlaylistSong = playlistSongService.createPlaylistSong(playlistSong);
            if(createdPlaylistSong!=null){
                result.put("status","Success");
                result.put("data",playlistSong);
            }else{
                result.put("status","Failed");
                result.put("detail","Playlist song không tồn tại");
            }
        } catch (Exception e) {
            result.put("status","Error");
            result.put("detail",e.toString());
        }
        return ResponseEntity.ok("result");
    }

    @PutMapping("/{playlistId}/{songId}")
    public ResponseEntity<?> updatePlaylistSong(@PathVariable Long playlistId, @PathVariable Long songId, @RequestBody PlaylistSong playlistSongDetails) {
        Map<String, Object> result = new HashMap<>();
        PlaylistSongId playlistSongId = new PlaylistSongId(playlistId, songId);
        try {
            PlaylistSong updatedPlaylistSong = playlistSongService.updatePlaylistSong(playlistSongId, playlistSongDetails);
            if(updatedPlaylistSong != null){
                result.put("status","Success");
                result.put("data",updatedPlaylistSong);
            }else{
                result.put("status","Failed");
                result.put("detail","Playlist song không tồn tại");
            }
        } catch (Exception e) {
            result.put("status","Error");
            result.put("detail",e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{playlistId}/{songId}")
    public ResponseEntity<?> deletePlaylistSong(@PathVariable Long playlistId, @PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        PlaylistSongId playlistSongId = new PlaylistSongId(playlistId, songId);
        try {
            PlaylistSong deletedPlaylistSong = playlistSongService.deletePlaylistSong(playlistSongId);
            result.put("status","Success");
            result.put("data",deletedPlaylistSong);
        } catch (Exception e) {
            result.put("status","Error");
            result.put("detail",e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
