package com.m2m.zing.api;

import com.m2m.zing.constant.ModelAttributes;
import com.m2m.zing.dto.LoginRequest;
import com.m2m.zing.dto.RegisterRequest;
import com.m2m.zing.model.User;
import com.m2m.zing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<User> users = userService.getAllUser();
            if (!users.isEmpty()) {
                result.put("status", "Success");
                result.put("data", users);
            } else {
                result.put("status", "Failed");
                result.put("detail", "Không Có Bất Kỳ Người Dùng Nào");
            }
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            User existUser;
            if (loginRequest.getUsername() != null) {
                existUser = userService.getByUserName(loginRequest.getUsername());
            } else {
                existUser = userService.getByEmail(loginRequest.getEmail());
            }
            if (existUser != null) {
                if (existUser.getPassword().equals(loginRequest.getPassword())) {
                    result.put("status", "success");
                    httpSession.setAttribute(ModelAttributes.CURRENT_USER, existUser);
                } else {
                    result.put("status", "failed");
                    result.put("detail", "Mật Khẩu Không Chính Xác");
                }
            } else {
                result.put("status", "failed");
                result.put("detail", "Sai Tên Tài Khoản Hoặc Mật Khẩu");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
//  check có tồn tại email hay chưa
            if (userService.getByEmail(registerRequest.getEmail()) != null) {
                result.put("status", "Failed");
                result.put("detail", "Email Đã Được Đăng Ký");
                return ResponseEntity.ok(result);
            }
// check có tồn tại username hay chưa
            if (userService.getByUserName(registerRequest.getUsername()) != null) {
                result.put("status", "Failed");
                result.put("detail", "Username Đã Tồn Tại");
                return ResponseEntity.ok(result);
            }
//  tạo account khi không phát sinh lỗi
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(registerRequest.getPassword());
            user.setEmail(registerRequest.getEmail());
            user.setAvatar(registerRequest.getAvatar());
            user.setFullName(registerRequest.getFullName());
            user.setProvider(registerRequest.getProvider());
            user.setGenders(registerRequest.isGenders());
            user.setRole("user");
            userService.createUser(user);
            result.put("status", "Success");
        } catch (Exception e) {
            result.put("status", "Error");
            result.put("detail", e.toString());
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody User updatedUserInfo) {
        Map<String, Object> result = new HashMap<>();
        try {
            User existingUser = userService.getUserById(userId);
            if (existingUser != null) {
                // Cập nhật thông tin người dùng với dữ liệu mới
                existingUser.setUsername(updatedUserInfo.getUsername());
                existingUser.setPassword(updatedUserInfo.getPassword());
                existingUser.setEmail(updatedUserInfo.getEmail());
                existingUser.setAvatar(updatedUserInfo.getAvatar());
                existingUser.setFullName(updatedUserInfo.getFullName());
                existingUser.setProvider(updatedUserInfo.getProvider());
                existingUser.setGenders(updatedUserInfo.isGenders());
                // Cập nhật thông tin người dùng vào cơ sở dữ liệu
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

