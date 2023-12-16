package com.m2m.zing.controller;

import com.m2m.zing.repository.AlbumRepository;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.service.impl.AlbumServiceImpl;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    SongServiceImpl songService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AlbumRepository albumService;


    @GetMapping("/index")
    public String index(Model model) throws Exception {
        model.addAttribute("currentSongs",songService.getAllCurrent(PageRequest.of(0,6)));
        model.addAttribute("authors",userService.getAllUser());
//        model.addAttribute("albums" ,albumService.findAll());
        return "user/index";
    }
}
