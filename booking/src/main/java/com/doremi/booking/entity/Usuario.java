package com.doremi.booking.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private UsuarioRole usuarioRole;
    
    public Usuario() {

    }

    public Usuario(String nombre, String email, String password, UsuarioRole usuarioRole) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
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
