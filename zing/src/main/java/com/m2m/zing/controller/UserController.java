package com.m2m.zing.controller;

import com.m2m.zing.model.User;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    SongServiceImpl songService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping()
    public String doGetDashBoard() throws Exception {
        return "user/dashboard";
    }

    @GetMapping("/history")
    public String doGetHistory() throws Exception {
        return "/user/history";
    }


    @GetMapping("/favorite")
    public String doGetFavorite() throws Exception {
        return "/user/history";
    }

    @GetMapping("/myProfile")
    public String doGetMyProfile() throws Exception {
        return "/user/myProfile";
    }

    @GetMapping("/editProfile")
    public String doGetEditProfile() throws Exception {
        return "/user/editProfile";
    }
}
