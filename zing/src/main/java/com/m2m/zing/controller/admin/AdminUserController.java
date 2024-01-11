package com.m2m.zing.controller.admin;

import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.RoleRepository;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.impl.AlbumServiceImpl;
import com.m2m.zing.service.impl.SongServiceImpl;
import com.m2m.zing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/user")
    public String getUserManagement(Model model){
        try {
            model.addAttribute("users",userService.getAllUser());
        } catch (Exception e) {
            System.out.println("Khong co user");
            throw new RuntimeException(e);
        }
        return "admin/user";
    }

    @GetMapping("/user/{id}")
    public String getEditUser(@PathVariable("id") Long id, Model model){
        try{
            model.addAttribute("user",userService.getUserById(id));
            model.addAttribute("userRoles",userRoleRepository.getUserRolesById(userService.getUserById(id).getUserId()));
            List<Role> allRoles = roleRepository.findAll();

            model.addAttribute("allRoles", allRoles);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Loi khi thuc hien");
        }
        return "admin/profile-edit";
    }

    @PostMapping("/user/{id}")
    public String postEditUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("selectedRoleId") Long roleId) {
        User userTemp = null;
        try {
            userTemp = userService.getUserById(id);
            userTemp.setFullName(user.getFullName());
            userTemp.setEmail(user.getEmail());
            userTemp.setPassword(user.getPassword());
            userTemp.setGenders(user.isGenders());
            userTemp.setActive(user.isActive());
            userTemp.setAvatar(user.getAvatar());
//            if(roleId == null){
//                System.out.println(roleId);
//                System.out.println(roleIdforNoRoles);
//                roleId = roleIdforNoRoles;
//            }
            Role role = roleRepository.findById(roleId).get();
            userService.addToUser(user.getEmail(),role.getName());
            System.out.println(user.getEmail()+" | "+role.getName());
            userService.update(userTemp);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Loi khi thuc hien");
        }
        return "redirect:/admin/user/" + userTemp.getUserId();
    }

    @GetMapping("/user/delete/{id}")
    public String getDelete(@PathVariable("id") Long id) throws Exception {
        User user = userService.getUserById(id);
        user.setActive(false);
        userService.update(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/user/insert")
    public String getInsert(Model model, @ModelAttribute("user") User user){
        user = new User();
        List<Role> role = roleRepository.findAll();
        model.addAttribute("allRoles",role);
        return "admin/profile-edit";
    }

    @PostMapping("/user/insert")
    public String postInsert(@ModelAttribute("user") User user, @RequestParam("selectedRoleIdInsert") Long roleId){
        User userTemp = new User();
        userTemp.setProvider(user.getProvider());
        userTemp.setUsername(user.getUsername());
        userTemp.setGenders(user.isGenders());
        userTemp.setFullName(user.getFullName());
        userTemp.setAvatar(user.getAvatar());
        userTemp.setEmail(user.getEmail());
        userTemp.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(roleId);
        Role roleTemp = roleRepository.findById(roleId).get();
        System.out.println(roleTemp);
        userService.createUser(userTemp);
        userService.addToUser(user.getEmail(),roleTemp.getName());

        return "redirect:/admin/user";
    }


}
