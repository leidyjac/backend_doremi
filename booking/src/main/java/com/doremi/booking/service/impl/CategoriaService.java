package com.doremi.booking.service.impl;

import com.doremi.booking.dto.entrada.categoria.CategoriaEntradaDto;
import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.entity.Categoria;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.repository.CategoriaRepository;
import com.doremi.booking.service.ICategoriaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CategoriaService implements ICategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);

    private CategoriaRepository categoriaRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private ModelMapper modelMapper;


    @Override
    public List<CategoriaSalidaDto> listarCategorias() {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        log.info("Esta es la lista de categorias :{}: ", listaCategorias);
        return listaCategorias.stream().map(this::maptoDtoSalidaCategoria).toList();

    }

    @Override
    public CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto categoria) throws ResourceNotCreatedException {
        if (categoriaRepository.findByNombre(categoria.getNombre()) == null) {

            Categoria nuevaCategoria = maptoDtoEntradaToCategoria(categoria);
            Categoria categoriaGuardada = categoriaRepository.save(nuevaCategoria);
            CategoriaSalidaDto categoriaAgregada = maptoDtoSalidaCategoria(categoriaGuardada);
            LOGGER.info("Categoría creada : {}", categoriaAgregada);
            return categoriaAgregada;

        } else {
            LOGGER.info("La categoría ya se encuentra en la base de datos");
            throw new ResourceNotCreatedException("La categoría ya se encuentra en la base de datos");
        }
    }

    @Transactional
    @Override
    public String eliminarCategoria(Long id) throws ResourceNotFoundException {
        Categoria categoriaABuscar = categoriaRepository.findById(id).orElse(null);
        if(categoriaABuscar != null){
            try {
                entityManager.createQuery("DELETE FROM Instrumento WHERE categoria = :categoria")
                        .setParameter("categoria", categoriaABuscar)
                        .executeUpdate();
            }catch(PersistenceException e) {
                LOGGER.error("No se puede eliminar la categoría con ID: {}. Hay instrumentos asociados que tienen reservas.", id);
                throw new IllegalStateException("No se puede eliminar la categoría. Hay instrumentos asociados que tienen reservas.");
            }
            categoriaRepository.deleteById(id);
            LOGGER.warn("Se eliminó la categoría con ID: {} ", id);
            return "Categoría eliminada exitosamente";
        }else{
            LOGGER.error("Categoría no encontrada con ID: {}", id);
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }

    }

    public CategoriaSalidaDto maptoDtoSalidaCategoria(Categoria categoria){
        return modelMapper.map(categoria, CategoriaSalidaDto.class);
    }

    public Categoria maptoDtoEntradaToCategoria(CategoriaEntradaDto categoria){
        return modelMapper.map(categoria, Categoria.class);
    }


    }






