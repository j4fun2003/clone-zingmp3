package com.m2m.zing.api.UIAPI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/ui")
public class SongUIAPI {

    @GetMapping("/song")
    public String getAddSong(){
        return "admin/song";
    }


}
