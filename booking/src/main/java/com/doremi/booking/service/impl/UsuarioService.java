package com.doremi.booking.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.doremi.booking.dto.entrada.modificacion.UsuarioCambioRoleEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.entity.User;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.exceptions.ResourceNotFoundException;
import com.doremi.booking.repository.UserRepository;
import com.doremi.booking.service.IUsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService{


    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);


    @Override
    public List<UsuarioSalidaDTO> listarUsuarios() {
        List<User> ListaUsuarios = userRepository.findAll();
        LOGGER.info("Listado de Usuarios {}", ListaUsuarios);
        return ListaUsuarios.stream().map(this::maptoDtoSalidaUsuario).toList();
      }

      @Override
      public UsuarioSalidaDTO cambiarRole(UsuarioCambioRoleEntradaDTO cambioRole)
              throws ResourceNotCreatedException, ResourceNotFoundException {
          // Buscar el usuario por su identificador único, en este caso, asumimos que es el nombre de usuario
          User usuario = userRepository.findByUsername(cambioRole.getUsername())
                  .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
      
          // Actualizar el rol del usuario con el nuevo valor proporcionado en el DTO
          usuario.setRole(cambioRole.getRole());
      
          // Guardar el usuario actualizado en la base de datos
          userRepository.save(usuario);
      
          // Mapear el usuario actualizado a un DTO de salida y devolverlo
          return maptoDtoSalidaUsuario(usuario);
      }

    private UsuarioSalidaDTO maptoDtoSalidaUsuario(User usuario) {
        return modelMapper.map(usuario, UsuarioSalidaDTO.class);
    }

    
    
}
