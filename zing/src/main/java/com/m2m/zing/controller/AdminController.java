package com.m2m.zing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin")
@Controller
public class AdminController {

    @GetMapping("")
    public String index(){
        return "admin/index";
    }
    @GetMapping("test1")
    public String index1(){
        return "admin/admin-category";
    }
    @GetMapping("test2")
    public String index2(){
        return "admin/admin-song";
    }
}
