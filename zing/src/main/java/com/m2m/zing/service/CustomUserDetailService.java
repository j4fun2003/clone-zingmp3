package com.m2m.zing.service;

import com.m2m.zing.model.CustomUserDetails;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return loadUser(username);
    }

    private UserDetails loadUser(String username){
        User user = userRepository.findByEmail(username);

        List<UserRole> roles = userRoleRepository.getUserRolesById(user.getUserId());
        if(user == null){
            throw new UsernameNotFoundException("Khong tim thay user "+username);
        }
        Collection<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>();


        for(UserRole userRole : roles){
            grantedAuthoritiesSet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
            System.out.println("Dang dang nhap voi quyen : "+userRole.getRole().getName());
        }
        return new User(user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getAvatar(),
                user.getFullName(),
                user.getCreateDate(),
                user.getProvider(),
                user.isActive(),
                user.isGenders(),
                user.getAlbums(),
                user.getSongs(),
                user.getPlaylists(),
                user.getFavorites(),
                user.getHistories(),
                user.getUserRoles(),
                user.getOtp(),
                user.getOtpGeneratedTime(),
                grantedAuthoritiesSet

        );
    }
}
