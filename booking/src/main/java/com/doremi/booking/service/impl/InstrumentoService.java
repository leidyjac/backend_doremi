package com.doremi.booking.service.impl;

import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.entity.Instrumento;
import com.doremi.booking.repository.InstrumentoRepository;
import com.doremi.booking.service.IInstrumentoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoService implements IInstrumentoService{

    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentoService.class);

    private final InstrumentoRepository instrumentoRepository;

    private final ModelMapper modelMapper;

    public InstrumentoService(InstrumentoRepository instrumentoRepository, ModelMapper modelMapper) {
        this.instrumentoRepository = instrumentoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InstrumentoSalidaDto agregarInstrumento(InstrumentoEntradaDto instrumento) {
        Instrumento instrumentoAgregado = instrumentoRepository.save(maptoEntity(instrumento));
        InstrumentoSalidaDto instrumentoSalidaDto = maptoDtoSalida(instrumentoAgregado);
        LOGGER.info("Instrumento guardado: {}", instrumentoSalidaDto);
        return instrumentoSalidaDto;
    }

    @Override
    public List<InstrumentoSalidaDto> listarInstrumentos() {
        List<Instrumento> listaInstrumentos = instrumentoRepository.findAll();
        LOGGER.info("Listado de instrumentos: {}", listaInstrumentos);
        return listaInstrumentos.stream().map(this::maptoDtoSalida).toList();
    }

    @Override
    public void eliminarInstrumento(Long id) { // colocar aquí la excepción
        Instrumento instrumentoABuscar = instrumentoRepository.findById(id).orElse(null);

        if(instrumentoABuscar != null){
            instrumentoRepository.deleteById(id);
            LOGGER.warn("Se eliminó el instrumento con id: {}", id);
        }else{
            LOGGER.error("Instrumento no encontrado con id: {}", id);
            //manejar aqui la excepción
        }

    }


    public Instrumento maptoEntity(InstrumentoEntradaDto instrumentoEntradaDto){
        return modelMapper.map(instrumentoEntradaDto,Instrumento.class);
    }

    public InstrumentoSalidaDto maptoDtoSalida(Instrumento instrumento){
        return modelMapper.map(instrumento, InstrumentoSalidaDto.class);
    }



}
