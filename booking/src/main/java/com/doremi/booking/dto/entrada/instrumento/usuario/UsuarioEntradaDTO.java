package com.doremi.booking.dto.entrada.instrumento.usuario;

import com.doremi.booking.entity.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioEntradaDTO {
    @NotNull(message = "El nombre del Usuario no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del Usuarioo")
    private String nombre;
    @NotNull(message = "El email no puede ser nulo")
    @NotBlank(message = "Debe especificarse el email")
    @Email(message = "Porfavor use un formato de email")
    private String email;
    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "La cuenta debe tener contraseña")
    @Size( min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    private String password;
    private UsuarioRole usuarioRole;
    
    public UsuarioEntradaDTO() {
    }

    public UsuarioEntradaDTO(
            String nombre, String email, String password, UsuarioRole usuarioRole) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioRole getUsuarioRole() {
        return usuarioRole;
    }

    public void setUsuarioRole(UsuarioRole usuarioRole) {
        this.usuarioRole = usuarioRole;
    }
    
}
