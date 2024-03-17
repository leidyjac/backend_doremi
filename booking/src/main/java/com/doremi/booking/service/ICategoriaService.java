package com.doremi.booking.service;

import com.doremi.booking.dto.entrada.categoria.CategoriaEntradaDto;
import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.entity.Categoria;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICategoriaService {

    List<CategoriaSalidaDto> listarCategorias();


    CategoriaSalidaDto agregarCategoria(CategoriaEntradaDto categoria) throws ResourceNotCreatedException;

    String eliminarCategoria(Long id) throws ResourceNotFoundException;
}
