package com.doremi.booking.dto.salida.imagen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImagenSalidaDto {

    private Long id;
    private String titulo;
    private String url;

}
