package com.m2m.zing.service.impl;

//import com.m2m.zing.dto.UserDetail;
import com.m2m.zing.model.Role;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import com.m2m.zing.num.Provider;
import com.m2m.zing.num.RoleEnum;
import com.m2m.zing.repository.RoleRepository;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.repository.UserRoleRepository;
import com.m2m.zing.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    UserRoleRepository userRoleRepository;




    @Override
    public User createUser(User user) {
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
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//        userRoleRepository.save(userRole);
//        user.getUserRoles().add(userRole);
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
    public List<UserRole> getUserRolesById(Long userId) {
        return userRoleRepository.getUserRolesById(userId);
    }

}
