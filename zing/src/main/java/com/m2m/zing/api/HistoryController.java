package com.m2m.zing.api;

import com.m2m.zing.model.History;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.HistoryService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    UserService userService;
    @Autowired
    SongService songService;


    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/add")
    public ResponseEntity<History> addToHistory(@RequestParam Long userId, @RequestParam Long songId) {
        // Fetch user and song details based on IDs
        // Assuming you have userService and songService to fetch details

        User user = userService.getUserById(userId);
        Song song = null;// songService.getSongById(songId);

        if (user != null && song != null) {
            History addedToHistory = historyService.addToHistory(user, song);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedToHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<History>> getHistoryByUser(@PathVariable Long userId) {
        User user = userService .getUserById(userId);
        if (user != null) {
            List<History> userHistory = historyService.getHistoryByUser(user);
            return ResponseEntity.ok(userHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearUserHistory(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            historyService.clearUserHistory(user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Other endpoints for history management
}
