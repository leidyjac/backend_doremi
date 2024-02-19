package com.doremi.booking.controller;

import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.service.IAccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accion")
public class AccionController {

    private IAccionService accionService;

    @Autowired
    public  AccionController(IAccionService accionService){
        this.accionService = accionService;
    }

    @Operation(summary = "Buscar accion por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento encontrado exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstrumentoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Nombre no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("true")
    public ResponseEntity<?> accionesPorEstado () {
        return new ResponseEntity<> (accionService.listarAcciones(Boolean.TRUE), HttpStatus.OK);

    }

}
