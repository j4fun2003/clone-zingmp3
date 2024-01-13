package com.m2m.zing.controller;

import com.m2m.zing.model.Role;
import com.m2m.zing.model.Singer;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.FirebaseService;
import com.m2m.zing.service.SingerService;
import com.m2m.zing.service.UserRoleService;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.ModuleElement;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    SongServiceImpl songService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    SingerService singerService;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    FirebaseService firebaseService;

    @GetMapping()
    public String doGetDashBoard(Model model) throws Exception {
        model.addAttribute("songs", songService.getAllSong());
        model.addAttribute("songNewRelease", songService.getTop5SongsNewRealease());
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
    public String doGetEditProfile(Model model) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            User userDetail = (User) authentication.getPrincipal();
            model.addAttribute("user", userDetail);
            model.addAttribute("userId",userDetail.getUserId());
            model.addAttribute("userRole",userRoleRepository);
            return "/user/profile-edit";
        } else {
            return "redirect:/auth/login/form";
        }
    }

    @PostMapping("/editProfile")
    public String doPostEditProfile(Model model, @ModelAttribute("user") User user,@RequestParam("avatarInput") MultipartFile avatar){
        try {
            User userTemp = userService.getUserById(user.getUserId());
            userTemp.setFullName(user.getFullName());
            userTemp.setEmail(user.getEmail());
            userTemp.setPassword(user.getPassword());
            userTemp.setGenders(user.isGenders());
            userTemp.setAvatar(avatar.getOriginalFilename());
            firebaseService.uploadFileToFirebaseStorage(avatar);
            userService.update(userTemp);
            System.out.println(userTemp);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi khi thuc hien");
        }
        return "redirect:/editProfile";
    }



    @GetMapping("/song-detail/{id}")
    public String doGetSongDetail(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("song", songService.getSongById(id));
        return "/user/songDetail";
    }

    @GetMapping("/singer-detail/{id}")
    public String doGetSingerDetail(Model model, @PathVariable Integer id ) throws  Exception{
        singerService.getSingerById(id);
        return "/user/singerDetail";
    }

}
