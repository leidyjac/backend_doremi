package com.doremi.booking.dto.salida.Usuario;

import com.doremi.booking.entity.UsuarioRole;

public class UsuarioSalidaDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private UsuarioRole usuarioRole;
    public UsuarioSalidaDTO() {
    }
    public UsuarioSalidaDTO(Long id, String nombre, String email, String password, UsuarioRole usuarioRole) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "UsuarioSalidaDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", usuarioRole=" + usuarioRole + "]";
    }

    
}
