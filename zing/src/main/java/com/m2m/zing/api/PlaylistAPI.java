package com.m2m.zing.api;

import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.User;
import com.m2m.zing.service.PlaylistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistAPI {

    @Autowired
    private PlaylistService playlistService;
    @Autowired
    HttpSession httpSession;

    // Endpoint tạo playlist
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPlaylist(@RequestBody String playlistName) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = (User) httpSession.getAttribute(ModelAttributes.CURRENT_USER);
            if(user  != null){
                Playlist createdPlaylist = playlistService.createPlaylist(user, playlistName);
                result.put("status", "Success");
                result.put("data", createdPlaylist);
            }
            else{
                result.put("status", "Failed");
                result.put("detail","Session Không Nhận Diện Được Người Dùng Hiện Tại");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPlaylistsByUser() {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = (User) httpSession.getAttribute(ModelAttributes.CURRENT_USER);
            if(user != null ){
                List<Playlist> playlists = playlistService.getPlaylistsByUser(user);
                if(!playlists.isEmpty()){
                    result.put("status", "Success");
                    result.put("data", playlists);
                }
                else{
                    result.put("status","Failed");
                    result.put("detail","Không có bất kì playlist nào cho người dùng này");
                }
            }else{
                result.put("status", "Failed");
                result.put("detail","Session Không Nhận Diện Được Người Dùng Hiện Tại");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint lấy thông tin playlist theo ID
    @GetMapping("/{playlistId}")
    public ResponseEntity<Map<String, Object>> getPlaylistById(@PathVariable Long playlistId) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để lấy playlist theo ID
            Playlist playlist = playlistService.getById(playlistId);
            result.put("status", "Success");
            result.put("data", playlist);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
