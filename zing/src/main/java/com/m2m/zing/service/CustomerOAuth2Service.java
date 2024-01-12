package com.m2m.zing.service;

import com.m2m.zing.model.CustomOAuth2;
import com.m2m.zing.model.CustomUserDetails;
import com.m2m.zing.model.User;
import com.m2m.zing.model.UserRole;
import com.m2m.zing.repository.UserRepository;
import com.m2m.zing.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerOAuth2Service extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        System.out.println("CustomOAuth2Service is called");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        User user  =  userRepository.findByEmail(oAuth2User.getName()).get();
        System.out.println("Ten cua oauth2 la: "+oAuth2User.getName());
        List<UserRole> roles = userRoleRepository.getUserRolesById(user.getUserId());
        System.out.println(roles);
        if(user == null){
            throw new UsernameNotFoundException("Khong tim thay user "+oAuth2User.getName());
        }
        Collection<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>();
        for(UserRole userRole : roles){
            grantedAuthoritiesSet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
            System.out.println("Dang dang nhap voi quyen : "+userRole.getRole().getName());
        }

        return new CustomOAuth2();
    }
}
