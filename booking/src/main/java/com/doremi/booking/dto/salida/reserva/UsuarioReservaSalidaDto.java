package com.doremi.booking.dto.salida.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UsuarioReservaSalidaDto {

    private Long idUsuario;

    private String nombre;

    private String apellido;


    @Override
    public String toString() {
        return "Id: " + idUsuario + ", nombre: " + nombre + ", apellido: " + apellido;
    }
}
