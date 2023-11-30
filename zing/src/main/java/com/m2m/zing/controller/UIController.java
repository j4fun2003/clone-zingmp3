package com.m2m.zing.controller;

import com.m2m.zing.model.User;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/getContent")
public class UIController {

    @Autowired
    UserService userService;

    @Autowired
    SongService songService;
    @GetMapping("/user/{fileName}")
    public String getContentUser(@PathVariable("fileName") String fileName, Model model) {
        try {
            User response = userService.getUserById(Long.parseLong("1"));
            System.out.println(response);
            model.addAttribute("authors",userService.getUserById((long) 1));
            model.addAttribute("songs",songService.getSongsByAuthorId((long) 1, PageRequest.of(0,6)));
            return "user/" + fileName;
        } catch(Exception e){

            log.error("Fail When call Controller api/getContent/user/{fileName} : ", e);
            return "";
        }
    }
}
