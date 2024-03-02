package com.doremi.booking.dto.entrada.imagen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
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
public class ImagenEntradaDto {

    @NotNull(message = "El título de la imagen no puede ser nulo")
    @NotBlank(message = "Debe ingresarse un título de la imagen")

    private String titulo;

    @NotNull(message = "El título de la imagen no puede ser nulo")
    @NotBlank(message = "Debe ingresarse un título para la imagen")
    @URL(message = "Debe ser una URL válida")
    private String url;

    private Long instrumento_id;


}
