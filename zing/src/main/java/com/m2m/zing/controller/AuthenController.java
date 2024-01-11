package com.m2m.zing.controller;

import com.m2m.zing.constant.ModelAttributes;
//import com.m2m.zing.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenController{

    @Autowired
    HttpSession httpSession;

    @RequestMapping("/login/form")
    public String doGetLogin(){
        return "user/login";
    }


    @RequestMapping("/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");

        return "redirect:/";
    }

    @RequestMapping("/login/error")
    public String getError(Model model){
        model.addAttribute("message","Incorrect usernamer or password");
        return "/user/login";
    }

    @RequestMapping("/register")
    public String getRegister(){
        return "/user/register";
    }

    @RequestMapping("/log-out")
    public String getLogout(Model model){
        model.addAttribute("message","Logout success");
        httpSession.setAttribute(ModelAttributes.CURRENT_USER,"");
        return "redirect:/auth/login";
    }
    @RequestMapping("/forgot-password")
    public String getForgotPassword(){
        httpSession.setAttribute(ModelAttributes.CURRENT_USER,"");
        return "/user/forgotPassword";
    }

    @RequestMapping("/confirmed-mail")
    public String confirmedMail(){
        return "/user/pages-confirm-mail";
    }
}
