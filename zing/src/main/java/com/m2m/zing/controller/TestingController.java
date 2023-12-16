package com.m2m.zing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestingController {
    @GetMapping
    public String getTest(){
        return "/user/test";
    }
}
