package com.doremi.booking.dto.salida.reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class InstrumentoReservaSalidaDto {

    private Long instrumento_id;
    private String nombre;


    @Override
    public String toString() {
        return "Id: " + instrumento_id + ", nombre: " + nombre;
    }

}


