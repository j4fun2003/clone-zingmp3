package com.m2m.zing.config;


import com.m2m.zing.model.User;
import com.m2m.zing.num.Provider;
import com.m2m.zing.service.CustomUserDetailService;
import com.m2m.zing.service.CustomerOAuth2Service;
import com.m2m.zing.service.UserService;
import com.m2m.zing.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    HttpSession session;
    @Autowired
    private UserService userService;


    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("123", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        return rememberMe;
    }

    @Bean
    public CustomUserDetailService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public CustomerOAuth2Service oAuth2Service() {
        return new CustomerOAuth2Service();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, RememberMeServices rememberMeServices) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/*", "/auth/**","/oauth/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/auth/login/form").permitAll()
                        .loginProcessingUrl("/auth/login/form")
                        .usernameParameter("email").passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login/error"))
                .oauth2Login(oauth -> oauth.loginPage("/auth/login/form")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/auth/login/error")
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(oAuth2Service()))
                        .successHandler(
                                (request, response,
                                 authentication) -> {
                                    var oidcUser = (DefaultOidcUser) authentication.getPrincipal();
                                    if (userService.findByEmail(oidcUser.getEmail()) == null) {
                                        User user = new User();
                                        user.setUsername(oidcUser.getEmail());
                                        user.setEmail(oidcUser.getEmail());
                                        user.setFullName(oidcUser.getFullName());
                                        user.setPassword(new BCryptPasswordEncoder().encode(oidcUser.getName()));
                                        user.setProvider(Provider.GOOGLE);
                                        userService.createUser(user);
                                        userService.addToUser(user.getEmail(), "USER");
                                    }

                                    response.sendRedirect("/");
                                }
                        ).permitAll())


                .rememberMe((remember) -> remember
                        .rememberMeServices(rememberMeServices)
                        .rememberMeParameter("remember")
                        .tokenValiditySeconds(86400))
                .logout(logout -> logout.logoutUrl("/auth/log-out").permitAll()
                        .logoutSuccessUrl("/auth/login/form"))
        ;
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/assets/**", "/templates/**");
    }

}
