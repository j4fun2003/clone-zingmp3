//package com.m2m.zing.api;
//
//import com.m2m.zing.model.Album;
//import com.m2m.zing.model.User;
//import com.m2m.zing.service.AlbumService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/albums")
//public class AlbumAPI {
//
//    @Autowired
//    private AlbumService albumService;
//
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> createAlbum(@RequestBody Album album) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Album createdAlbum = albumService.createAlbum(album);
//            result.put("status", "Success");
//            result.put("data", createdAlbum);
//        } catch (Exception e) {
//            result.put("status", "Error");
//            result.put("detail", e.toString());
//        }
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/{albumId}")
//    public ResponseEntity<Map<String, Object>> getAlbumById(@PathVariable Long albumId) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Album fetchedAlbum = albumService.getAlbumById(albumId);
//            if (fetchedAlbum != null) {
//                result.put("status", "Success");
//                result.put("data", fetchedAlbum);
//            } else {
//                result.put("status", "Failed");
//                result.put("detail", "Album not found");
//            }
//        } catch (Exception e) {
//            result.put("status", "Error");
//            result.put("detail", e.toString());
//        }
//        return ResponseEntity.ok(result);
//    }
//
//    @PutMapping("/{albumId}")
//    public ResponseEntity<Map<String, Object>> updateAlbum(@PathVariable Long albumId, @RequestBody Album albumDetails) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            Album updatedAlbum = albumService.updateAlbum(albumId, albumDetails);
//            if (updatedAlbum != null) {
//                result.put("status", "Success");
//                result.put("data", updatedAlbum);
//            } else {
//                result.put("status", "Failed");
//                result.put("detail", "Album not found");
//            }
//        } catch (Exception e) {
//            result.put("status", "Error");
//            result.put("detail", e.toString());
//        }
//        return ResponseEntity.ok(result);
//    }
//
//    @DeleteMapping("/{albumId}")
//    public ResponseEntity<Map<String, Object>> deleteAlbum(@PathVariable Long albumId) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            albumService.deleteAlbum(albumId);
//            result.put("status", "Success");
//        } catch (Exception e) {
//            result.put("status", "Error");
//            result.put("detail", e.toString());
//        }
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Map<String, Object>> getAlbumsByUser(@PathVariable Long userId) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            User user = new User();
//            user.setUserId(userId);
//            List<Album> userAlbums = albumService.getAlbumsByUser(user);
//            result.put("status", "Success");
//            result.put("data", userAlbums);
//        } catch (Exception e) {
//            result.put("status", "Error");
//            result.put("detail", e.toString());
//        }
//        return ResponseEntity.ok(result);
//    }
//}
