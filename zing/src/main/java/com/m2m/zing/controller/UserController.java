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
@RequestMapping("user")
public class UserController {

    @Autowired
    SongServiceImpl songService;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/{userId}")
    public String index(Model model, @PathVariable("userId") Long userId) throws Exception {
        model.addAttribute("songs",songService.getSongsByAuthorId(userId,PageRequest.of(0,6)));
        model.addAttribute("authors",userService.getUserById(userId));
        return "user/singer";
    }

}
