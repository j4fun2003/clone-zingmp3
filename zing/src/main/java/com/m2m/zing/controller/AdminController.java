package com.m2m.zing.controller;

import com.m2m.zing.model.Genre;
import com.m2m.zing.model.Song;
import com.m2m.zing.service.*;
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

    @Autowired
    AlbumService albumService;


    @GetMapping("/dashboard")
    public String getDashboardManagement(Model model) throws Exception {
        model.addAttribute("singers", singerService.getAllSinger());
        model.addAttribute("songs", songService.getAllSong());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("albums", albumService.getAll());
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
        model.addAttribute("singers", singerService.getAllSinger());
        model.addAttribute("song", songService.getSongById(id));
        return "admin/editSong";
    }

    @GetMapping("/song-add")
    public String addSong(Model model) throws Exception {
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("singers", singerService.getAllSinger());
        return "admin/addSong";
    }

    @GetMapping("/edit-singer/{id}")
    public String editSong(Model model, @PathVariable Integer id) throws Exception {
        model.addAttribute("singer", singerService.getSingerById(id));
        return "admin/editSinger";
    }


    @GetMapping("/album")
    public String getAlBum(Model model) {
        model.addAttribute("albums", albumService.getAll());
        return "/admin/album";
    }


    @GetMapping("/edit-album/{albumId}")
    public String getEditAlbum(Model model, @PathVariable Long albumId) throws Exception {
        model.addAttribute("album", albumService.getAlbumById(albumId));
        model.addAttribute("singers", singerService.getAllSinger());
        model.addAttribute("songs", songService.getAllSong());
        model.addAttribute("albumSongs", songService.getSongByAlbum(albumId));
        return "admin/editAlbums";
    }

    @GetMapping("/add-album")
    public String getAddAlbum(Model model) throws Exception {
        model.addAttribute("singers", singerService.getAllSinger());
        model.addAttribute("songs", songService.getAllSong());
        return "admin/addAlbums";
    }
}