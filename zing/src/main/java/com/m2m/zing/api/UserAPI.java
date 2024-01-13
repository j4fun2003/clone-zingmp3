package com.m2m.zing.api;

import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.dto.ChangePassRequest;
import com.m2m.zing.model.Song;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.service.SongService;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    SongService songService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PutMapping("/change-pass/{userId}")
    public ResponseEntity<?> changePass(@PathVariable("userId") Long userId, @RequestBody ChangePassRequest changePass) {
        Map<String, Object> result = new HashMap<>();
        try {
            User existingUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (existingUser != null) {
                if(passwordEncoder.matches(changePass.getPassword(), existingUser.getPassword())){
                    if(changePass.getNewPass().equals(changePass.getConfirm())){
                        existingUser.setPassword(passwordEncoder.encode(changePass.getNewPass()));
                    }
                }
                User updatedUser = userService.updateUser(userId, existingUser);
                result.put("status", "Success");
                result.put("data", updatedUser);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Không tồn tại người dùng");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
            return ResponseEntity.ok(result);
        }
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            User deleteUser = userService.getUserById(userId);
            if (deleteUser != null) {
                deleteUser.setActive(false);
                userService.updateUser(deleteUser.getUserId(), deleteUser);
                result.put("status", "Success");
            } else {
                result.put("status", "Failed");
                result.put("detail", "Không tồn tại người dùng");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
            return ResponseEntity.ok(result);
        }
    }
}

