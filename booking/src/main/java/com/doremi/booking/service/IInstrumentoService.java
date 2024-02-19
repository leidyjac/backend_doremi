package com.doremi.booking.service;

import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IInstrumentoService {

    InstrumentoSalidaDto agregarInstrumento (InstrumentoEntradaDto instrumento) throws ResourceNotCreatedException;

    List<InstrumentoSalidaDto> listarInstrumentos();

    List<InstrumentoSalidaDto> listarInstrumentosHome();

    void eliminarInstrumento(Long id) throws ResourceNotFoundException;

    InstrumentoSalidaDto buscarPorNombre (String nombre);
}
