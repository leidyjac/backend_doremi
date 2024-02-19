package com.doremi.booking.controller;

import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoMessageSalidaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.service.IInstrumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {

    private IInstrumentoService instrumentoService;

    @Autowired

    public InstrumentoController(IInstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    //Endpoint para agregar un instrumento

    @Operation(summary = "Registro de un nuevo instrumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instrumento agregado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstrumentoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("agregar")
    public ResponseEntity<InstrumentoSalidaDto> agregarInstrumento(@Valid @RequestBody InstrumentoEntradaDto instrumento) throws ResourceNotCreatedException {
        return new ResponseEntity<>(instrumentoService.agregarInstrumento(instrumento), HttpStatus.CREATED);
    }

    @Operation(summary = "Listado de instrumentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de instrumentos obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstrumentoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listar")
    public ResponseEntity<List<InstrumentoSalidaDto>> listarInstrumentos() {
        return new ResponseEntity<>(instrumentoService.listarInstrumentos(), HttpStatus.OK);
    }

    @Operation(summary = "Listado de instrumentos aleatorios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de instrumentos aleatorios obtenido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InstrumentoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("listaraleatorios")
    public ResponseEntity<List<InstrumentoSalidaDto>> listarInstrumentoshome() {
        return new ResponseEntity<>(instrumentoService.listarInstrumentosHome(), HttpStatus.OK);
    }

    @Operation(summary = "Eliminación de un instrumento por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instrumento eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Instrumento no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarInstrumento (@PathVariable Long id) throws ResourceNotFoundException {
        instrumentoService.eliminarInstrumento(id);
        InstrumentoMessageSalidaDto instrumentoMessageSalidaDto = new InstrumentoMessageSalidaDto("El Instrumento ha sido eliminado Satisfactoriamente");
        return new ResponseEntity<>(instrumentoMessageSalidaDto, HttpStatus.OK); //REVISAR
    }

    @Operation(summary = "Buscar instrumento por nombre")
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
    @GetMapping("buscarPorNombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre (@PathVariable String nombre) {
        return new ResponseEntity<> (instrumentoService.buscarPorNombre(nombre), HttpStatus.OK);

    }

}
