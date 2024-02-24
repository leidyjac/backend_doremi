package com.doremi.booking.dto.entrada.instrumento;

import com.doremi.booking.dto.entrada.imagen.ImagenEntradaDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentoEntradaDto {
    @NotNull(message = "El nombre del instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del instrumento")
    private String nombre;
    @NotNull(message = "El tipo de instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse el tipo de instrumento")
    private String tipo;

    @NotNull(message = "La descripción del instrumento no puede ser nulo")
    @NotBlank(message = "Debe especificarse una descripción del instrumento")
    private String descripcion;

    private double precioDia;

    @NotNull(message = "La imagen del instrumento no puede ser nula")
    @Valid
    private ImagenEntradaDto imagen;
}
