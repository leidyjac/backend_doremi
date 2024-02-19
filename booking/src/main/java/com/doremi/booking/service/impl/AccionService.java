package com.doremi.booking.service.impl;

import com.doremi.booking.dto.salida.accion.AccionSalidaDto;
import com.doremi.booking.entity.Accion;
import com.doremi.booking.repository.AccionRepository;
import com.doremi.booking.service.IAccionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
@Slf4j
public class AccionService implements IAccionService {

    private final AccionRepository accionRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<AccionSalidaDto> listarAcciones(Boolean estado) {
        List<Accion> listaAccion = accionRepository.findByEstado(estado);
        log.info("Esta es la lista de acciones :{}: ", listaAccion);
        return listaAccion.stream().map(this::maptoDtoSalida).toList();
    }


    public AccionSalidaDto maptoDtoSalida(Accion accion){
        return modelMapper.map(accion, AccionSalidaDto.class);
    }
}
