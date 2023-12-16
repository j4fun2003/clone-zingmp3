package com.m2m.zing.api.UIAPI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ui")
public class SongUIAPI {

    @GetMapping("/song-add")
    public String getAddSong(){
        return "admin/song_add";
    }

    @GetMapping("/song-edit")
    public String getEditSong(){
        return "admin/song_edit";
    }

    @GetMapping("/song-list")
    public String getListSong(){
        return "admin/song_list";
    }

}
