package com.doremi.booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doremi.booking.dto.entrada.modificacion.UsuarioCambioRoleEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.service.impl.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Listado de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listar")
    public ResponseEntity<List<UsuarioSalidaDTO>> listarUsuarios() {
        List<UsuarioSalidaDTO> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Operation(summary = "Cambio de Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role modificado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("cambioRole")
    public ResponseEntity<UsuarioSalidaDTO> cambiarRole(@Valid @RequestBody UsuarioCambioRoleEntradaDTO cambioRole) throws ResourceNotCreatedException, ResourceNotFoundException {
        UsuarioSalidaDTO usuarioModificado = usuarioService.cambiarRole(cambioRole);
        return new ResponseEntity<>(usuarioModificado, HttpStatus.OK);
    }
}
