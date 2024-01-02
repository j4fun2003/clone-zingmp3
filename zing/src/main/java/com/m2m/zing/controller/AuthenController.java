package com.m2m.zing.controller;

import com.m2m.zing.constant.ModelAttributes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenController{

    @Autowired
    HttpSession httpSession;
    @GetMapping("/login")
    public String getSignIn(){
        return "/user/login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "/user/register";
    }

    @GetMapping("/log-out")
    public String getLogout(){
        httpSession.setAttribute(ModelAttributes.CURRENT_USER,"");
        return "redirect:/auth/login";
    }
    @GetMapping("/forgot-password")
    public String getForgotPassword(){
        httpSession.setAttribute(ModelAttributes.CURRENT_USER,"");
        return "/user/forgotPassword";
    }

    @GetMapping("/confirmed-mail")
    public String confirmedMail(){
        return "/user/pages-confirm-mail";
    }
}
