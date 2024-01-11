package com.m2m.zing.controller.admin;

import com.m2m.zing.model.Album;
import com.m2m.zing.service.impl.AlbumServiceImpl;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminAlbumController {

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    private UserServiceImpl userService;

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

}
