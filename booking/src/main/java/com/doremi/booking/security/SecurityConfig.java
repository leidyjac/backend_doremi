package com.doremi.booking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

  
     @Bean
      public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
      }

     @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

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

