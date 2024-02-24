package com.doremi.booking.dto.salida.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaSalidaDto {

    private Long id;
    private String nombre;
    private String imagen;
}
