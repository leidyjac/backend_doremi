package com.doremi.booking.service;


import java.util.List;

import com.doremi.booking.dto.entrada.instrumento.usuario.UsuarioEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.exceptions.ResourceNotCreatedException;

public interface IUsuarioService {
    UsuarioSalidaDTO agregarUsuario (UsuarioEntradaDTO Usuario) throws ResourceNotCreatedException;
    List<UsuarioSalidaDTO> listarUsuarios(); 
    UsuarioSalidaDTO buscarPorEmail (String email);
} 
