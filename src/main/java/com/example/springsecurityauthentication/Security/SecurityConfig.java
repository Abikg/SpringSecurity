package com.example.springsecurityauthentication.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
//        List<SimpleGrantedAuthority> ROLES = new ArrayList<>();
//        ROLES.add(new SimpleGrantedAuthority("ROLE_"+"ADMIN"));
//        ROLES.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
//        UserDetails admin= User.withUsername("Abik")
//                .password(encoder.encode("Abik"))
////                .roles("ADMIN")
//                .authorities(ROLES.get(0))
//                .build();
//        UserDetails user= User.withUsername("Anish")
//                .password(encoder.encode("Anish"))
////                .roles("USER")
//                .authorities(ROLES.get(1))
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
          return new UserInfoDetailsService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/ss/home","/ss/new").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/ss/**").authenticated()
                .and().formLogin()
                .and()
                .logout(logout ->
                        logout.
                                logoutUrl("/ss/logout").
                                invalidateHttpSession(true).
                                clearAuthentication(true).
                                permitAll()
                )
                .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
