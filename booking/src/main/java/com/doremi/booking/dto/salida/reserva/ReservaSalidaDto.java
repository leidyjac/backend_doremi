package com.doremi.booking.dto.salida.reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaSalidaDto {

    private Long idReserva;

    private UsuarioReservaSalidaDto usuarioReservaSalidaDto;

    private InstrumentoReservaSalidaDto instrumentoReservaSalidaDto;

    private LocalDate fechaInicial;

    private LocalDate fechaFinal;

    @Override
    public String toString() {
        return "Id: " + idReserva + ", usuarioReservaSalidaDto: " + usuarioReservaSalidaDto + ", instrumentoReservaSalidaDto: " + instrumentoReservaSalidaDto + ", fecha inicial: "+ fechaInicial + ", fecha final: " + fechaFinal;
    }


}
