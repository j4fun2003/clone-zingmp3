package com.m2m.zing.controller;

import com.m2m.zing.constant.ModelAttributes;
//import com.m2m.zing.service.AuthenticationService;
import com.m2m.zing.dto.RegisterRequest;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.time.Duration;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthenController{

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/login/form")
    public String doGetLogin(){
        return "user/login";
    }

    @PostMapping("/register")
    public String doPostRegister(@ModelAttribute("user") RegisterRequest user, Model model) {
        try {
            if (userService.findByEmail(user.getEmail()).getEmail().equals(user.getEmail())) {
                model.addAttribute("message", "Email already in used, please use another!");
                return "user/register";
            }
        } catch (Exception e) {
            model.addAttribute("message",   userService.register(user));
            model.addAttribute("hiddenMail",user.getEmail());
        } return "user/pages-confirm-mail";
    }

    @GetMapping("/verify-account")
    public String doPutVerify(@RequestParam String email, @RequestParam String otp,Model model){
        model.addAttribute("message",userService.verifyAccount(email,otp));
        return "user/pages-confirm-mail";
    }

    @GetMapping("/generate-otp")
    public String doPutGenerateOtp(@RequestParam String email,Model model){
        model.addAttribute("message",userService.regenerateOtp(email));
        return "user/pages-confirm-mail";
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
    public String getRegister(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("message","It's quick and easy");
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
        return "/user/forgotPassword";
    }

    @PostMapping("/forgot-password")
    public String doPostForgotPassword(@RequestParam String email,Model model){
        try {
            if (userService.findByEmail(email).getEmail().equals(email)) {
                model.addAttribute("message", userService.forgotPass(email));
            }
        }catch(Exception e){
            model.addAttribute("message","Email not found");
        }
        model.addAttribute("email",email);
        return "user/pages-confirm-mail";
    }

    @GetMapping("/set-password")
    public String doGetSetPass(@RequestParam String email, @RequestParam String otp,Model model){
        User user = userRepository.findByEmail(email);
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            model.addAttribute("email",email);
            return "user/newPass";
        }else{
            model.addAttribute("message","Have error, please try again!");
            return "user/pages-confirm-mail";
        }
    }

    @PostMapping("/set-password")
    public String doPostSetPass(@RequestParam("email") String email, @RequestParam("password") String password,Model model){
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        user.setPassword( new BCryptPasswordEncoder().encode(password));
        userService.update(user);
        model.addAttribute("message","Your password was changed!");
        return "user/pages-confirm-mail";
    }



    @RequestMapping("/confirmed-mail")
    public String confirmedMail(){
        return "/user/pages-confirm-mail";
    }
}
