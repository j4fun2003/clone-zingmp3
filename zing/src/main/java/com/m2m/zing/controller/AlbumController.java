package com.m2m.zing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("album")
public class AlbumController {

    @GetMapping("/")
    public String doGetAlbum(Model model){
        return "user/album";
    }

    @GetMapping
    public String doGet(Model model){
        return "redirect:/album";
    }


}
