package com.doremi.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doremi.booking.dto.entrada.usuario.UsuarioEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.entity.Usuario;
import com.doremi.booking.entity.UsuarioRole;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.repository.UsuarioRepository;
import com.doremi.booking.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioSalidaDTO agregarUsuario(UsuarioEntradaDTO usuarioEntradaDTO) throws ResourceNotCreatedException {
        if (!isValidEmail(usuarioEntradaDTO.getEmail())) {
            throw new ResourceNotCreatedException("Invalid email format");
        }

        if (!isValidPassword(usuarioEntradaDTO.getPassword())) {
            throw new ResourceNotCreatedException("Password does not meet security requirements");
        }

        if (usuarioRepository.findByEmail(usuarioEntradaDTO.getEmail()) != null) {
            LOGGER.info("El usuario ya se encuentra en la base de datos");
            throw new ResourceNotCreatedException("El usuario ya se encuentra en la base de datos");
        }

        Usuario usuario = mapToEntity(usuarioEntradaDTO);
        usuario.setPassword(hashPassword(usuarioEntradaDTO.getPassword())); // Hash password

        try {
            Usuario usuarioAgregado = usuarioRepository.save(usuario);
            UsuarioSalidaDTO usuarioSalidaDTO = mapToDtoSalida(usuarioAgregado);
            LOGGER.info("Usuario guardado: {}", usuarioSalidaDTO);
            return usuarioSalidaDTO;
        } catch (Exception e) {
            LOGGER.error("Error al agregar usuario", e);
            throw new ResourceNotCreatedException("Error al crear el usuario");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[^A-Za-z0-9].*");
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Usuario mapToEntity(UsuarioEntradaDTO usuarioEntradaDTO) {
        return modelMapper.map(usuarioEntradaDTO, Usuario.class);
    }

    private UsuarioSalidaDTO mapToDtoSalida(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioSalidaDTO.class);
    }

    @Override
    public List<UsuarioSalidaDTO> listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        LOGGER.info("Listado de Usuarios: {}", listaUsuarios);
        return listaUsuarios.stream().map(this::mapToDtoSalida).toList();
    }

    @Override
    public UsuarioSalidaDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        LOGGER.info("Este es el usuario que buscas: {}", usuario);
        return mapToDtoSalida(usuario);
    }
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Usuario usuario = usuarioRepository.findByEmail(username);
         if (usuario == null) {
             throw new UsernameNotFoundException("Usuario no encontrado con email: " + username);
    }

        // Obtener el rol del usuario
        UsuarioRole role = usuario.getUsuarioRole();

        // Crear una autoridad basada en el rol del usuario
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name());

        // Crear la lista de autoridades
        List<GrantedAuthority> authorities = new ArrayList<>();
         authorities.add(authority);

        return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .authorities(authorities)
            .build();
}

    


    
}

