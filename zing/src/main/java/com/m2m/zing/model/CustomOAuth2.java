package com.m2m.zing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomOAuth2 implements OAuth2User {

    private OAuth2User auth;

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }



    public OAuth2User getUser() {
        return auth;
    }

    public void setUser(OAuth2User user) {
        this.auth = auth;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return auth.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth.getAuthorities();
    }

    @Override
    public String getName() {
        return auth.getAttribute("email");
    }

    public String getEmail() {
        return auth.getAttribute("email");
    }

    public String getFullName() {
        return auth.getAttribute("fullName");
    }
}
