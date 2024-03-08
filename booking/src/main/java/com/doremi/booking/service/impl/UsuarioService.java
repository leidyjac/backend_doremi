package com.doremi.booking.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.doremi.booking.dto.entrada.usuario.UsuarioEntradaDTO;
import com.doremi.booking.dto.salida.Usuario.UsuarioSalidaDTO;
import com.doremi.booking.entity.Usuario;
import com.doremi.booking.exceptions.ResourceNotCreatedException;
import com.doremi.booking.repository.UsuarioRepository;
import com.doremi.booking.service.IUsuarioService;


@Service
public class UsuarioService implements IUsuarioService, UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);
    private UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
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

        Usuario usuario = maptoEntity(usuarioEntradaDTO);
        usuario.setPassword(hashPassword(usuarioEntradaDTO.getPassword())); // Hash password

        try {
            Usuario usuarioAgregado = usuarioRepository.save(usuario);
            UsuarioSalidaDTO usuarioSalidaDTO = maptoDtoSalida(usuarioAgregado);
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
        java.util.regex.Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            return false;
        }  
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (!password.matches(".*[^A-Za-z0-9].*")) {
            return false;
        }
        return true;
    }
    

    private String hashPassword(String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
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
        Usuario usuario = usuarioRepository.findByEmail(email);
        LOGGER.info("Este es el usuario que buscas :{}: ", usuario);
        return maptoDtoSalida(usuario);
    }

    // Métodos UserDetails no implementados
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

    @Override
    public boolean isAccountNonExpired() {
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}
