package com.doremi.booking.service.impl;

import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.entity.Categoria;
import com.doremi.booking.repository.CategoriaRepository;
import com.doremi.booking.service.ICategoriaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class CategoriaService implements ICategoriaService {

    private CategoriaRepository categoriaRepository;
    private ModelMapper modelMapper;


    @Override
    public List<CategoriaSalidaDto> listarCategorias() {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        log.info("Esta es la lista de categorias :{}: ", listaCategorias);
        return listaCategorias.stream().map(this::maptoDtoSalidaCategoria).toList();

    }

    public CategoriaSalidaDto maptoDtoSalidaCategoria(Categoria categoria){
        return modelMapper.map(categoria, CategoriaSalidaDto.class);
    }

    }






