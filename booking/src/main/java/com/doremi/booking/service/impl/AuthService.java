package com.doremi.booking.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doremi.booking.dto.entrada.usuario.LoginRequestDTO;
import com.doremi.booking.dto.entrada.usuario.RegisterRequestDTO;
import com.doremi.booking.dto.salida.Usuario.AuthResponse;
import com.doremi.booking.entity.Role;
import com.doremi.booking.entity.User;
import com.doremi.booking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    @SuppressWarnings("null")
    public AuthResponse register(RegisterRequestDTO request) {
        User user = User.builder()
        .nombre(request.getNombre())
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

        userRepository.save(user);

        return AuthResponse.builder()
             .token(jwtService.getToken(user))
             .build();
    }
    
      
}