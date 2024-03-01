package com.doremi.booking.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.doremi.booking.entity.Usuario;
import com.doremi.booking.entity.UsuarioRole;
import com.doremi.booking.repository.UsuarioRepository;
import com.doremi.booking.service.impl.UsuarioService;

@SpringBootTest
public class UsarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void testLoadUserByUsername_UsuarioExistente() {
        String username = "marcela.hermosa@gmail.com";
        Usuario usuario = new Usuario("Marcela", "Administrador", username, "contraseñaEncriptada", UsuarioRole.ROLE_ADMIN);

        when(usuarioRepository.findByEmail(username)).thenReturn(Optional.of(usuario));
        UserDetails userDetails = usuarioService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());

    }

    @Test
    public void testLoadUserByUsername_UsuarioNoExistente() {
        String username = "usuario_no_existente@gmail.com";

        when(usuarioRepository.findByEmail(username)).thenReturn(Optional.empty());

        try {
            usuarioService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            assertEquals("Usuario no encontrado: " + username, e.getMessage());
        }
    }
}
