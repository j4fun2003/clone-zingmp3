package com.m2m.zing.controller.admin;

import com.m2m.zing.model.CustomUserDetails;
import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.RoleRepository;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public String getDashboardManagement(Model model){
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String admin(){
        return "admin/dashboard";
    }

    @GetMapping("/song")
    public String getSongManagement(){
        return "admin/song";
    }



    @GetMapping("/genre")
    public String getGenreManagement(){
        return "admin/genre";
    }

}
