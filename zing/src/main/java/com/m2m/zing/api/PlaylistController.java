package com.m2m.zing.api;

import com.m2m.zing.model.Playlist;
import com.m2m.zing.model.User;
import com.m2m.zing.service.PlaylistService;
import com.m2m.zing.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserService userService; // Inject UserService

    public PlaylistController(PlaylistService playlistService, UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createPlaylist(@RequestParam Long userId, @RequestParam String playlistName) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Playlist createdPlaylist = playlistService.createPlaylist(user, playlistName);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlaylist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Playlist>> getPlaylistsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(user);
            return ResponseEntity.ok(userPlaylists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
