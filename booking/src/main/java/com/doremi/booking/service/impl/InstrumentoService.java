package com.doremi.booking.service.impl;

import com.doremi.booking.dto.entrada.imagen.ImagenEntradaDto;
import com.doremi.booking.dto.entrada.instrumento.InstrumentoEntradaDto;
import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.dto.salida.imagen.ImagenSalidaDto;
import com.doremi.booking.dto.salida.instrumento.InstrumentoSalidaDto;
import com.doremi.booking.entity.Categoria;
import com.doremi.booking.entity.Imagen;
import com.doremi.booking.entity.Instrumento;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.repository.CategoriaRepository;
import com.doremi.booking.repository.ImagenRepository;
import com.doremi.booking.repository.InstrumentoRepository;
import com.doremi.booking.service.IInstrumentoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class InstrumentoService implements IInstrumentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentoService.class);

    private final InstrumentoRepository instrumentoRepository;

    private final CategoriaRepository categoriaRepository;

    private final ImagenRepository imagenRepository;

    private final ModelMapper modelMapper;

    public InstrumentoService(InstrumentoRepository instrumentoRepository, CategoriaRepository categoriaRepository, ImagenRepository imagenRepository, ModelMapper modelMapper) {
        this.instrumentoRepository = instrumentoRepository;
        this.categoriaRepository = categoriaRepository;
        this.imagenRepository = imagenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InstrumentoSalidaDto agregarInstrumento(InstrumentoEntradaDto instrumento) throws ResourceNotCreatedException {
        if ((instrumentoRepository.findByNombre(instrumento.getNombre()) == null)) {

            Categoria categoria = categoriaRepository.findById(instrumento.getCategoria())
                    .orElseThrow(() -> new ResourceNotCreatedException("La categoría no existe"));

            LOGGER.info ("Categoria -> " + categoria);
            Instrumento instrumentoARegistrar = maptoEntity(instrumento);

            instrumentoARegistrar.setCategoria(categoria);


            Instrumento instrumentoAgregado = instrumentoRepository.save (instrumentoARegistrar);

            LOGGER.info("instrumento agregado -> " + instrumentoAgregado);

            List<Imagen> imagen = imagenRepository.saveAll (mapToEntityImagenList(instrumento.getImagen(),instrumentoAgregado.getInstrumento_id()));
            LOGGER.info("Imagen guardada -> " + imagen);

            InstrumentoSalidaDto instrumentoSalidaDto = maptoDtoSalida(instrumentoAgregado);

            LOGGER.info("Instrumento guardado: {}", instrumentoSalidaDto);

            return instrumentoSalidaDto;
        } else {

            LOGGER.info("El instrumento ya se encuentra en la base de datos");
            throw new ResourceNotCreatedException("El instrumento ya se encuentra en la base de datos");
        }

    }
    @Override
    public List<InstrumentoSalidaDto> listarInstrumentos() {
        List<Instrumento> listaInstrumentos = instrumentoRepository.findAll();

        LOGGER.info("Listado de instrumentos: {}", listaInstrumentos);
        return listaInstrumentos.stream().map(this::maptoDtoSalida).toList();
    }

    @Override
    public void eliminarInstrumento(Long id) throws ResourceNotFoundException {
        Instrumento instrumentoABuscar = instrumentoRepository.findById(id).orElse(null);

        if (instrumentoABuscar != null) {
            instrumentoRepository.deleteById(id);
            LOGGER.warn("Se eliminó el instrumento con id: {}", id);
        } else {
            LOGGER.error("Instrumento no encontrado con id: {}", id);
            throw new ResourceNotFoundException("El instrumento no se encuentra con el id " + id);
        }

    }
    @Override
    public InstrumentoSalidaDto buscarPorNombre(String nombre) {
        Instrumento instrumento = instrumentoRepository.findByNombre(nombre);
        LOGGER.info("Este es el instumento que estas buscando :{}: ", instrumento);
        return maptoDtoSalida(instrumento);
    }

    @Override
    public List<InstrumentoSalidaDto> listarInstrumentosHome() {
        List<Instrumento> listaInstrumentos = instrumentoRepository.findAll();
        Collections.shuffle(listaInstrumentos);
        List<Instrumento> instrumentosAleatorios = listaInstrumentos.subList(0, Math.min(10, listaInstrumentos.size()));
        LOGGER.info("Listado de instrumentos aleatorios: {}", instrumentosAleatorios);
        return instrumentosAleatorios.stream().map(this::maptoDtoSalida).toList();
    }

    public Instrumento maptoEntity(InstrumentoEntradaDto instrumentoEntradaDto) {
        return modelMapper.map(instrumentoEntradaDto, Instrumento.class);
    }

    public List<Imagen> mapToEntityImagenList(List<ImagenEntradaDto> imagenEntradaDtoList, Long idInstrumento) {

        imagenEntradaDtoList = imagenEntradaDtoList.stream()
                .map(imagenEntradaDto -> {
                    imagenEntradaDto.setInstrumento_id(idInstrumento);
                    return imagenEntradaDto;
                })
                .collect(Collectors.toList());

        return imagenEntradaDtoList
                .stream()
                .map(imagenEntradaDto -> {
                    Imagen imagen = new Imagen();
                    imagen.setTitulo(imagenEntradaDto.getTitulo());
                    imagen.setUrl(imagenEntradaDto.getUrl());
                    imagen.setInstrumento(imagenEntradaDto.getInstrumento_id());
                    return imagen;
                })
                .collect(Collectors.toList());
    }


    public InstrumentoSalidaDto maptoDtoSalida(Instrumento instrumento) {
        InstrumentoSalidaDto instrumentoSalidaDto = new InstrumentoSalidaDto();
        instrumentoSalidaDto.setInstrumento_id(instrumento.getInstrumento_id());
        instrumentoSalidaDto.setNombre(instrumento.getNombre());
        instrumentoSalidaDto.setCategoria(new CategoriaSalidaDto(instrumento.getCategoria().getCategoria_id(),instrumento.getCategoria().getNombre(),instrumento.getCategoria().getImagen()));
        instrumentoSalidaDto.setDescripcion(instrumento.getDescripcion());
        List<ImagenSalidaDto> imagenSalidaDto = modelMapper.map(instrumento.getImagenes(), new TypeToken<List<ImagenSalidaDto>>() {}.getType());
        instrumentoSalidaDto.setImagen(imagenSalidaDto);

        return instrumentoSalidaDto;
    }

}