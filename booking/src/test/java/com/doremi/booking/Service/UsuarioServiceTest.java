package com.doremi.booking.Service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.doremi.booking.entity.Usuario;
import com.doremi.booking.entity.UsuarioRole;
import com.doremi.booking.repository.UsuarioRepository;
import com.doremi.booking.service.impl.UsuarioService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void testLoadUserByUsername_UsuarioExistente() {
        String username = "marcela.hermosa@gmail.com";
        Usuario usuario = new Usuario("Marcela", "marcela.hermosa@gmail.com", "contraseñaEncriptada", UsuarioRole.ROLE_ADMIN);

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

