package com.doremi.booking.dto.entrada.instrumento;

import com.doremi.booking.dto.entrada.imagen.ImagenEntradaDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "La descripción no puede ser nula")
    @NotBlank(message = "Debe especificarse una descripción")
    private String descripcion;

    @NotNull(message = "El ID de la categoría no puede ser nulo")
    private Long categoria;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor que 0")
    private double precioDia;

    @NotNull(message = "La imagen del instrumento no puede ser nula")
    @Valid
    private ImagenEntradaDto imagen;
}
