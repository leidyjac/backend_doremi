package com.doremi.booking.service;

import com.doremi.booking.dto.salida.accion.AccionSalidaDto;

import java.util.List;

public interface IAccionService {

    List<AccionSalidaDto> listarAcciones(Boolean estado);
}
