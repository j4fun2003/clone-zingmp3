package com.m2m.zing.controller.admin;

import com.m2m.zing.model.Genre;
import com.m2m.zing.model.Song;
import com.m2m.zing.service.GenreService;
import com.m2m.zing.service.SingerService;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    SongService songService;
    @Autowired
    GenreService genreService;
    @Autowired
    SingerService singerService;
    @Autowired
    UserService userService;


    @GetMapping("")
    public String getDashboardManagement() {
        return "admin/dashboard";
    }

    @GetMapping("/song")
    public String getSongManagement(Model model) throws Exception {
        model.addAttribute("songs", songService.getAllSong());

        return "admin/song";
    }

    @GetMapping("/albums")
    public String getGenreManagement() {
        return "admin/albums";
    }

    @GetMapping("/singer")
    public String getSingerManagement(Model model) throws Exception {
        model.addAttribute("singers", singerService.getAllSinger());
        return "admin/singer";
    }

    @GetMapping("/add-singer")
    public String getAddSinger() {
        return "admin/addSinger";
    }

    @GetMapping("/edit-song/{id}")
    public String editSong(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("songs", songService.getAllSong());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("singers", userService.getAllUser());
        model.addAttribute("song", songService.getSongById(id));
        return "admin/editSong";
    }
    @GetMapping("/song-add")
    public String addSong(Model model) throws Exception{
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("singers", userService.getAllUser());
        return "admin/addSong";
    }
    @GetMapping("/edit-singer/{id}" )
    public String editSong(Model model, @PathVariable Integer id) throws  Exception{
        model.addAttribute("singer", singerService.getSingerById(id));
        return "admin/editSinger";
    }
}