package com.m2m.zing.service.impl;

import com.m2m.zing.dto.UserDetail;
import com.m2m.zing.model.User;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService /*,UserDetailsService*/ {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) throws Exception {
        user.setPassword(user.getPassword());
       return  userRepository.save(user);
    }

//    public User createUser(User user) throws Exception {
//        user.setActive(true);
//        user.setCreateDate(LocalDateTime.now());
//        return userRepository.save(user);
//    }

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

    @Override
    public User getByUserName(String username) throws Exception{
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByEmail(String email)throws Exception {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        return userRepository.findAll();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userInfo = Optional.ofNullable(userRepository.findByUsername(username));
//        return userInfo.map(UserDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//    }
}
