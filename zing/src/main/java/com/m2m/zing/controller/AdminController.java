package com.m2m.zing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String getDashboardManagement(){
        return "admin/dashboard";
    }

    @GetMapping("/song")
    public String getSongManagement(){
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
