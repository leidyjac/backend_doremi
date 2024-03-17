package com.doremi.booking.dto.entrada.categoria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CategoriaEntradaDto {

    @NotNull(message = "El nombre de la categoria no puede ser nulo")
    @NotBlank(message = "Debe ingresarse un nombre de la categoría")
    private String nombre;

    @NotNull(message = "La descripcion de la categoria no puede ser nulo")
    @NotBlank(message = "Debe ingresarse una descripcion de la categoría")
    private String descripcion;

    @NotNull(message = "La imagen no puede ser nula")
    @NotBlank(message = "Debe ingresarse una imagen")
    @URL(message = "Debe ser una URL válida")
    private String imagen;

}
