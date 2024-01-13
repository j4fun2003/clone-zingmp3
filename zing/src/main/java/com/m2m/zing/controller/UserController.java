package com.m2m.zing.controller;

import com.google.api.Authentication;
import com.google.api.Http;
import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.model.*;
import com.m2m.zing.model.idClass.FavoriteId;
import com.m2m.zing.service.AlbumService;
import com.m2m.zing.service.FavoriteService;
import com.m2m.zing.service.HistoryService;
import com.m2m.zing.service.SingerService;
import com.m2m.zing.service.UserRoleService;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.ModuleElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    HistoryService historyService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    AlbumService albumService;

    @GetMapping()
    public String doGetDashBoard(Model model) throws Exception {
        model.addAttribute("songs", songService.getAllSong());
        model.addAttribute("songNewRelease", songService.getTop5SongsNewRealease());
        model.addAttribute("singers", singerService.getAllSinger());
        model.addAttribute("albums", albumService.getAll());
        return "user/dashboard";
    }

    @GetMapping("/history")
    public String doGetHistory(Model model) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            List<History> histories = new ArrayList<>();
            histories = historyService.getHistoryByUser(user);
            List<Song> songs = new ArrayList<>();
            histories.forEach(history -> {
                songs.add(history.getSong());
            });
            model.addAttribute("songs", songs);
        }
        return "/user/history";
    }


    @GetMapping("/favorite")
    public String doGetFavorite(Model model) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Favorite> favorites = favoriteService.getByUser(user);
        if (user != null) {
            List<Song> songs = new ArrayList<>();
            favorites.forEach(favorite -> {
                songs.add(favorite.getSong());
            });
            model.addAttribute("songs", songs);
        }
        return "/user/favorites";
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
        Song song = songService.getSongById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isFavorite = false;
        if (user != null) {
            FavoriteId favoriteId = new FavoriteId();
            favoriteId.setSong(id);
            favoriteId.setUser(user.getUserId());
            Favorite favorite = favoriteService.getByFavoriteId(favoriteId);
            if (favorite != null) {
                isFavorite = true;
            }
        }
        model.addAttribute("isFavorite", isFavorite);
        model.addAttribute("song", song);
        return "/user/songDetail";
    }

    @GetMapping("/singer-detail/{id}")
    public String doGetSingerDetail(Model model, @PathVariable Integer id) throws Exception {
        Singer singer = singerService.getSingerById(id);
        List<Song> songs = songService.getSongBySinger(singer);
        model.addAttribute("singer", singer);
        model.addAttribute("songs", songs);
        return "/user/singerDetail";
    }

    @GetMapping("/album-detail/{id}")
    public String doGetAlbumDetail(@PathVariable Long id, Model model) throws  Exception{
        model.addAttribute("albums", albumService.getAlbumById(id));
        model.addAttribute("albumsSong", songService.getSongByAlbum(id));
        return "user/albumDetail";
    }
}
