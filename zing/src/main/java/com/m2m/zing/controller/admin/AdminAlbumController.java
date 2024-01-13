package com.m2m.zing.controller.admin;

import com.m2m.zing.model.Album;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.service.FirebaseService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/album")
    public String doGetAlbum(Model model){
        model.addAttribute("albums",albumService.getAll());
        return "admin/album";
    }

    @GetMapping("/album/{id}")
    public String doGetAlbumDetail(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("album",albumService.getAlbumById(id));
        model.addAttribute("albumService", albumService);
        model.addAttribute("songs",songService.getSongByAlbum(id));
        return "admin/album-detail";
    }

    @PostMapping("/album/{id}")
    public String doPostAlbumDetail(@PathVariable("id") Long id, Model model, @ModelAttribute("album")Album album) {
        Album albumTemp = new Album();
        try{
            albumTemp = albumService.getAlbumById(id);
            albumTemp.setTitle(album.getTitle());
            albumService.createAlbum(albumTemp);
        } catch (Exception e) {
            System.out.println("Lỗi sửa album");
            throw new RuntimeException(e);
        }
        return "redirect:/admin/album/"+albumTemp.getAlbumId();
    }

    @GetMapping("/album/insert")
    public String doGetAlbumDetail(Model model) throws Exception {
        model.addAttribute("users",userService.getAllUser());
        model.addAttribute("album", new Album());
        return "admin/album-insert";
    }

    @PostMapping("/album/insert")
    public String doPostAlbumDetail(Model model, @ModelAttribute("album") Album album, @RequestParam("selectImg") MultipartFile selectImg, @RequestParam("selectAuthor") Long id) throws Exception {
        User user = userService.getUserById(id);
        album.setImage(selectImg.getOriginalFilename());
        firebaseService.uploadFileToFirebaseStorage(selectImg);
        album.setAuthor(user);
        try{
            List<Song> song = songService.getSongsByAuthor_UserId(id);

        }catch(Exception e){

        }
        albumService.createAlbum(album);
        return "admin/album";
    }

}
