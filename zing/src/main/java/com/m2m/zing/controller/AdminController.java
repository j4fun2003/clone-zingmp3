package com.m2m.zing.controller;

import com.m2m.zing.model.Genre;
import com.m2m.zing.model.Song;
import com.m2m.zing.service.GenreService;
import com.m2m.zing.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    SongService songService;
    @Autowired
    GenreService genreService;


    @GetMapping("")
    public String getDashboardManagement(){
        return "admin/dashboard";
    }

    @GetMapping("/song")
    public String getSongManagement(Model model) throws Exception{
        List<Song> songs =  songService.getAllSong();
        List<Genre> genres = genreService.getAllGenre();
        model.addAttribute("songs",songs);
        model.addAttribute("genres",genres);
        return "admin/song";
    }

    @GetMapping("/user")
    public String getUserManagement(){
        return "admin/user";
    }

    @GetMapping("/genre")
    public String getGenreManagement(){
        return "admin/genre";
    }
}
