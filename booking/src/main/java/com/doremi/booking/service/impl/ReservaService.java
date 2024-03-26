package com.doremi.booking.service.impl;

import com.doremi.booking.dto.entrada.reseva.ReservaEntradaDto;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.dto.salida.reserva.InstrumentoReservaSalidaDto;
import com.doremi.booking.dto.salida.reserva.ReservaSalidaDto;
import com.doremi.booking.dto.salida.reserva.UsuarioReservaSalidaDto;
import com.doremi.booking.entity.Instrumento;
import com.doremi.booking.entity.Reserva;
import com.doremi.booking.entity.User;
import com.doremi.booking.exceptions.BadRequestException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.repository.ReservaRepository;
import com.doremi.booking.service.IReservaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService implements IReservaService {

    private final Logger LOGGER = LoggerFactory.getLogger(ReservaService.class);

    private final ReservaRepository reservaRepository;

    private final ModelMapper modelMapper;

    private InstrumentoService instrumentoService;

    private UsuarioService usuarioService;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, ModelMapper modelMapper, InstrumentoService instrumentoService, UsuarioService usuarioService) {
        this.reservaRepository = reservaRepository;
        this.modelMapper = modelMapper;
        this.instrumentoService = instrumentoService;
        this.usuarioService = usuarioService;
    }

   /*@Override
    public Boolean fechasDisponibles(Long instrumentoId, LocalDate fechaInicial, LocalDate fechaFinal){
        List<Reserva> reservas = reservaRepository.findByInstrumento_IdAndFechaInicialBetweenAndFechaFinalBetween(instrumentoId, fechaInicial, fechaFinal);
        return reservas.isEmpty();
    }*/

    @Override
    public ReservaSalidaDto reservarInstrumento(ReservaEntradaDto reservaEntradaDto) throws BadRequestException, ResourceNotFoundException {
        ReservaSalidaDto reservaSalidaDto = null;
        UsuarioSalidaDTO usuario = usuarioService.buscarUsuarioPorId(reservaEntradaDto.getUsuarioId());InstrumentoSalidaDto instrumento = instrumentoService.buscarInstrumentoPorId(reservaEntradaDto.getInstrumentoId());

        if(instrumento == null || usuario == null){
            if(instrumento == null && usuario == null){
                LOGGER.error("El usuario y el instrumento ingresados no se encuentran en la base de datos");
                throw new BadRequestException("El usuario y el instrumento ingresados no se encuentran en la base de datos");
            }else if (instrumento == null){
                LOGGER.error ("El instrumento ingresado no se encuentra en la base de datos");
                throw new BadRequestException("El instrumento ingresado no se encuentra en la base de datos");
            }else{
                LOGGER.error ("El usuario ingresado no se encuentra en la base de datos");
            }
        }
       /*if(!fechasDisponibles(reservaEntradaDto.getInstrumentoId(), reservaEntradaDto.getFechaInicial(), reservaEntradaDto.getFechaFinal())){
            LOGGER.error("Las fechas deseadas no están disponibles. Por favor ingresa otro rango de fechas");
            throw new BadRequestException("Las fechas deseadas no están disponibles. Por favor ingresa otro rango de fechas");
        }*/
        else{
            Reserva reservaNueva = reservaRepository.save(mapDtoEntradaAEntidad(reservaEntradaDto));
            reservaSalidaDto = entidadADtoSalida(reservaNueva);
            LOGGER.info("Reserva registrada con exito: {}", reservaSalidaDto);
        }
        return reservaSalidaDto;
    }

    public Reserva mapDtoEntradaAEntidad(ReservaEntradaDto reservaEntradaDto) throws ResourceNotFoundException {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(null); // Excluir idReserva

        // Obtener la entidad User a partir del ID
        UsuarioSalidaDTO usuarioSalidaDto = usuarioService.buscarUsuarioPorId(reservaEntradaDto.getUsuarioId());
        reserva.setUsuario(maptoDtoSalidaAUsuario(usuarioSalidaDto));

        // Obtener la entidad Instrumento a partir del ID
        InstrumentoSalidaDto instrumentosalidaDto = instrumentoService.buscarInstrumentoPorId(reservaEntradaDto.getInstrumentoId());
        reserva.setInstrumento(maptoDtoSalidaAInstrumento(instrumentosalidaDto));

        reserva.setFechaInicial(reservaEntradaDto.getFechaInicial());
        reserva.setFechaFinal(reservaEntradaDto.getFechaFinal());

        return reserva;
    }

    private UsuarioReservaSalidaDto usuarioSalidaDtoASalidaReservaDto(Long id) throws ResourceNotFoundException {
        return modelMapper.map(usuarioService.buscarUsuarioPorId(id), UsuarioReservaSalidaDto.class);
    }

    private InstrumentoReservaSalidaDto instrumentoSalidaDtoASalidaReservaDto(Long id) throws ResourceNotFoundException {
        return modelMapper.map(instrumentoService.buscarInstrumentoPorId(id), InstrumentoReservaSalidaDto.class);
    }


    private ReservaSalidaDto entidadADtoSalida(Reserva reserva) throws ResourceNotFoundException {
        ReservaSalidaDto reservaSalidaDto = modelMapper.map(reserva, ReservaSalidaDto.class);
        reservaSalidaDto.setUsuarioReservaSalidaDto(usuarioSalidaDtoASalidaReservaDto(reserva.getUsuario().getUsuario_id()));
        reservaSalidaDto.setInstrumentoReservaSalidaDto(instrumentoSalidaDtoASalidaReservaDto(reserva.getInstrumento().getInstrumento_id()));
        return reservaSalidaDto;
    }

    private User maptoDtoSalidaAUsuario(UsuarioSalidaDTO usuarioSalidaDTO) {
        return modelMapper.map(usuarioSalidaDTO, User.class);
    }

    private Instrumento maptoDtoSalidaAInstrumento(InstrumentoSalidaDto instrumentoSalidaDTO) {
        return modelMapper.map(instrumentoSalidaDTO, Instrumento.class);
    }
}
