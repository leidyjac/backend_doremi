package com.doremi.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doremi.booking.entity.Usuario;
import com.doremi.booking.service.impl.UsuarioService;

@RestController
public class RegistroController {
    private UsuarioService usuarioService;

    @Autowired
    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuario);

        if (usuarioGuardado != null) {
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al registrar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

    

