package com.doremi.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doremi.booking.dto.entrada.usuario.UsuarioEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.service.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private IUsuarioService usuarioService;

    @Autowired
    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
 * @param usuario
 * @return
 * @throws ResourceNotCreatedException
 */
@Operation(summary  = "Registro de un nuevo Usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario Agregado correctamente",
                     content = {@Content(mediaType = "aplication/json",
                               schema = @Schema(implementation = UsuarioSalidaDTO.class))}),
        @ApiResponse(responseCode = "400", description = "Bad Request",
                               content = @Content),
        @ApiResponse(responseCode = "500", description = "Server error",
                               content = @Content)                       
    })
    @PostMapping("agregar")
    public ResponseEntity<UsuarioSalidaDTO> agregarUsuario(@Valid @RequestBody UsuarioEntradaDTO usuario) throws ResourceNotCreatedException {
        return new ResponseEntity<>(usuarioService.agregarUsuario(usuario), HttpStatus.CREATED);
    }
    @Operation(summary = "Listado de Usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Usuarios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listar")
    public ResponseEntity<List<UsuarioSalidaDTO>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }
    @Operation(summary = "Buscar Usuario por email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioSalidaDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Mail no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Mail no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("buscarPorEmail/{email}")
    public ResponseEntity<?> buscarPorEmail (@PathVariable String email) {
        return new ResponseEntity<> (usuarioService.buscarPorEmail(email), HttpStatus.OK);

    }
}
