package com.doremi.booking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.doremi.booking.dto.entrada.usuario.UsuarioEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.entity.Usuario;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.repository.UsuarioRepository;
import com.doremi.booking.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);
    private UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;


    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UsuarioSalidaDTO agregarUsuario(UsuarioEntradaDTO usuarioEntradaDTO) throws ResourceNotCreatedException {
        if((usuarioRepository.findByEmail(usuarioEntradaDTO.getEmail())== null)) {
            Usuario usuarioAgregado = usuarioRepository.save(maptoEntity(usuarioEntradaDTO));
            UsuarioSalidaDTO usuarioSalidaDTO = maptoDtoSalida(usuarioAgregado);
            LOGGER.info("Usuario guardado: {}", usuarioSalidaDTO);
            return usuarioSalidaDTO;
        }
        else {
            LOGGER.info("El usuario ya se encuentra en la base de datos");
            throw new ResourceNotCreatedException("El usuario ya se encuentra en la base de datos");
        }
    }
    public Usuario maptoEntity(UsuarioEntradaDTO usuarioEntradaDTO) {
        return modelMapper.map(usuarioEntradaDTO, Usuario.class);
    }

    public UsuarioSalidaDTO maptoDtoSalida(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioSalidaDTO.class);
    }

    @Override
    public List<UsuarioSalidaDTO> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        LOGGER.info("Listado de Usuarios", listaUsuarios);
        return listaUsuarios.stream().map(this::maptoDtoSalida).toList();
    
    }

    @Override
    public UsuarioSalidaDTO buscarPorEmail(String email) {
        Usuario usuario= usuarioRepository.findByEmail(email);
        LOGGER.info("Este es el usuarioque buscas :{}: ", usuario);
        return maptoDtoSalida(usuario);
    }
    
}
