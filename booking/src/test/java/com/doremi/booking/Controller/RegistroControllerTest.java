
package com.doremi.booking.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.doremi.booking.controller.RegistroController;
import com.doremi.booking.entity.Usuario;
import com.doremi.booking.service.impl.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistroController.class)
@AutoConfigureMockMvc
public class RegistroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void registrarUsuario_ReturnsCreated() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setNombre("Test User");
        usuario.setPassword("password123");

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("hashedPassword");
        Mockito.when(usuarioService.registrarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/registro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        // Assert
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void registrarUsuario_ReturnsInternalServerError() throws Exception {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setNombre("Test User");
        usuario.setPassword("password123");

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("hashedPassword");
        Mockito.when(usuarioService.registrarUsuario(Mockito.any(Usuario.class))).thenReturn(null);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/registro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)));

        // Assert
        resultActions.andExpect(status().isInternalServerError());
    }
}
