package com.doremi.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.doremi.booking.entity.Usuario;
import com.doremi.booking.service.impl.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

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
    
    @Operation(summary = "Registro de Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de Usuario correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> registroUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosRegistrados();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}


    

