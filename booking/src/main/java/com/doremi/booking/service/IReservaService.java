package com.doremi.booking.service;

import com.doremi.booking.dto.entrada.reseva.ReservaEntradaDto;
import com.doremi.booking.dto.salida.reserva.ReservaSalidaDto;
import com.doremi.booking.exceptions.BadRequestException;

public interface IReservaService {

    ReservaSalidaDto reservarInstrumento (ReservaEntradaDto reserva) throws BadRequestException;
}
