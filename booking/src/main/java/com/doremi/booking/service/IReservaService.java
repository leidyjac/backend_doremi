package com.doremi.booking.service;

import com.doremi.booking.dto.entrada.reseva.ReservaEntradaDto;
import com.doremi.booking.dto.salida.reserva.ReservaSalidaDto;
import com.doremi.booking.entity.Reserva;
import com.doremi.booking.exceptions.BadRequestException;
import com.doremi.booking.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface IReservaService {

    ReservaSalidaDto reservarInstrumento (ReservaEntradaDto reserva) throws BadRequestException, ResourceNotFoundException;

   //Boolean fechasDisponibles(Long instrumentoId, LocalDate fechaInicial, LocalDate fechaFinal);
}
