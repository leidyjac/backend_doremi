package com.doremi.booking.controller;

import com.doremi.booking.dto.entrada.categoria.CategoriaEntradaDto;
import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoMessageSalidaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.service.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> listaCategorias() {
        return new ResponseEntity<>(categoriaService.listarCategorias(), HttpStatus.OK);

    }

    @Operation(summary = "Registro de una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría agregada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoriaSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("agregar")
    public ResponseEntity<CategoriaSalidaDto> agregarCategoria(@Valid @RequestBody CategoriaEntradaDto categoria) throws ResourceNotCreatedException {
        return new ResponseEntity<>(categoriaService.agregarCategoria(categoria), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminación de una categoría por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            categoriaService.eliminarCategoria(id);
            return new ResponseEntity<>("Categoria eliminada exitosamente", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar la categoría con ID: " + id + ". Hay instrumentos asociados que tienen reservas.");
        }
    }
}




