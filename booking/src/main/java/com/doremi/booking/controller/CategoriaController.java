package com.doremi.booking.controller;

import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.service.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private ICategoriaService categoriaService;

    @Operation(summary = "Listado de todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de categorias obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listar")
    public ResponseEntity<?> listaCategorias () {
        return new ResponseEntity<> (categoriaService.listarCategorias(), HttpStatus.OK);

    }

}




