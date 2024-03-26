package com.doremi.booking.dto.salida.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UsuarioReservaSalidaDto {

    private Long usuario_id;

    private String nombre;

    private String apellido;


    @Override
    public String toString() {
        return "Id: " + usuario_id + ", nombre: " + nombre + ", apellido: " + apellido;
    }
}
