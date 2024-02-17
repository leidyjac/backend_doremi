package com.doremi.booking.service;

import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;

import java.util.List;

public interface IInstrumentoService {

    InstrumentoSalidaDto agregarInstrumento (InstrumentoEntradaDto instrumento);

    List<InstrumentoSalidaDto> listarInstrumentos();

    void eliminarInstrumento(Long id);
}
