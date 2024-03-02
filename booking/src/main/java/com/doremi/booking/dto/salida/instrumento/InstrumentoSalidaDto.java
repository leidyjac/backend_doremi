package com.doremi.booking.dto.salida.instrumento;

import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.dto.salida.imagen.ImagenSalidaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstrumentoSalidaDto {
    private Long instrumento_id;
    private String nombre;
    private CategoriaSalidaDto categoria;
    private String descripcion;
    private double precioDia;
    private List<ImagenSalidaDto> imagen;

    @Override
    public String toString() {
        return "Id: " + instrumento_id + " - Nombre: " + nombre + " - Tipo: " + categoria + " - Descripción: " + descripcion + " - Precio:: " + precioDia + " -Imagen: " + imagen;
    }

}
