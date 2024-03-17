package com.doremi.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doremi.booking.dto.entrada.usuario.LoginRequestDTO;
import com.doremi.booking.dto.entrada.usuario.RegisterRequestDTO;
import com.doremi.booking.dto.salida.Usuario.AuthResponse;
import com.doremi.booking.service.impl.AuthService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequestDTO request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
