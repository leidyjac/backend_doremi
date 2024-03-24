package com.doremi.booking.dto.salida.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaSalidaDto {

    private Long categoria_id;
    private String nombre;

    private String descripcion;
    private String imagen;

    @Override
    public String toString() {
        return "Id: " + categoria_id + " - Nombre: " + nombre + " - Descripcion: " + descripcion + " - Imagen: " + imagen;
    }
}
