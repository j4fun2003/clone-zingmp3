package com.m2m.zing.service.impl;

//import com.m2m.zing.dto.UserDetail;
import com.m2m.zing.dto.RegisterRequest;
import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import com.m2m.zing.num.Provider;
import com.m2m.zing.num.RoleEnum;
import com.m2m.zing.repository.RoleRepository;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.UserService;
import com.m2m.zing.util.MailUtil;
import com.m2m.zing.util.OtpUtil;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService /*,UserDetailsService*/ {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private MailUtil emailUtil;

    @Autowired
    UserRoleRepository userRoleRepository;



    @Override
    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setCreateDate(LocalDateTime.now());
        return  userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public void addToUser(String username, String name) {
        User user =  userRepository.findByEmail(username);
        Role role = roleRepository.findByName(name);
        if (user != null) {

            try{
                UserRole userRole = userRoleRepository.findByUserId(user.getUserId());
                if (userRoleRepository.existsById(userRole.getId())) {
                    userRole.setRole(role);
                    userRoleRepository.save(userRole);
//                  user.getUserRoles().add(userRole);
                } else {
                    userRole = new UserRole(user, role);
                    userRoleRepository.save(userRole);
//                  user.getUserRoles().add(userRole);
                }
            }catch(Exception e){
                UserRole userRole = new UserRole(user, role);
//              user.getUserRoles().add(userRole);
                userRoleRepository.save(userRole);

                System.out.println("Không tìm thấy user");
            }
        }
        userRepository.save(user);
    }


    @Override
    public User getUserById(Long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    @Override
    public User updateUser(Long userId, User userDetails) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userDetails.setUserId(userId);
            return userRepository.save(userDetails);
        }
        return null; // User not found
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(userRepository::delete);
    }


//    @Override
//    public User getByEmail(String email)throws Exception {
//        return userRepository.findByEmail(email);
//    }

    @Override
    public List<User> getAllUser() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(registerRequest.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        user.setGenders(registerRequest.isGenders());
        user.setOtp(otp);
        user.setActive(false);
        user.setProvider(Provider.APPLICATION);
        user.setCreateDate(LocalDateTime.now());
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        addToUser(user.getEmail(),"USER");
        return "Email sent... please verify account within 1 minute in email: ";
    }

    @Override
    public List<UserRole> getUserRolesById(Long userId) {
        return userRoleRepository.getUserRolesById(userId);
    }

    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    @Override
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    @Override
    public String forgotPass(String email) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpChangePassword(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found with this email :"+email));
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please check your email to recover password!";
    }


}
