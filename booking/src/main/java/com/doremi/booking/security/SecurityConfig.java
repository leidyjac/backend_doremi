package com.doremi.booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final PasswordEncoder passwordEncoder;
    

        

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/usuarios/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/instrumentos/agregar").hasRole("ADMIN")
            .anyRequest().permitAll()
        )
        .formLogin(login -> login
            .loginPage("/login")
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        )
        .exceptionHandling(exceptionHandling -> exceptionHandling
            .accessDeniedPage("/access-denied")
        )
        .build();
     }
               

                
                  
    }

