package com.m2m.zing.api;

import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.model.History;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.HistoryService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryAPI {

    @Autowired
    private HistoryService historyService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    SongService songService;

    // Endpoint thêm bản ghi vào lịch sử
    @PostMapping("/{songId}")
    public ResponseEntity<Map<String, Object>> addToHistory(@PathVariable Long songId) {
        Map<String, Object> result = new HashMap<>();
        try {

            User user = (User) httpSession.getAttribute(ModelAttributes.CURRENT_USER);
            Song song = songService.getSongById(songId);
            if(song == null || user == null){
                result.put("status", "Failed");
                result.put("detail", "Người Dùng Hoặc Bài Hát Không Tồn Tại ");
            }else{
                History addedHistory = historyService.addToHistory(user, song);
                result.put("status", "Success");
                result.put("data", addedHistory);
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getHistoryByUser(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Gọi service để lấy lịch sử theo userId
            User user = (User) httpSession.getAttribute(ModelAttributes.CURRENT_USER);
            List<History> userHistory = historyService.getHistoryByUserId(userId);
            result.put("status", "Success");
            result.put("data", userHistory);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    // Endpoint xóa lịch sử của user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> clearUserHistory(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = (User) httpSession.getAttribute(ModelAttributes.CURRENT_USER);
            // Gọi service để xóa lịch sử của userId
            historyService.clearUserHistory(user);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }
}
