package com.doremi.booking.service;

import com.doremi.booking.dto.salida.categoria.CategoriaSalidaDto;
import com.doremi.booking.entity.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<CategoriaSalidaDto> listarCategorias();
}
