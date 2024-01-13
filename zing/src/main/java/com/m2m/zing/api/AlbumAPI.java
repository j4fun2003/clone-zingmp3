package com.m2m.zing.api;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.service.FirebaseService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/albums")
public class AlbumAPI {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private SongService songService;


    @PostMapping("/insert")
    public ResponseEntity<?> insertAlbum(@ModelAttribute("album") Album album,
                                         @RequestParam("selectImg") MultipartFile selectImg,
                                         @RequestParam("selectAuthor") Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.getUserById(id);
            album.setImage(selectImg.getOriginalFilename());
            firebaseService.uploadFileToFirebaseStorage(selectImg);
            album.setAuthor(user);
            albumService.createAlbum(album);

            result.put("status", "success");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "error");
            result.put("detail", e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}