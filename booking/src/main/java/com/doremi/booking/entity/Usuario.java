package com.doremi.booking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size( max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Column
    private String nombre;
        @NotBlank(message = "El email no puede estar vacio")
    @Size( max = 50, message = "El email no puede tener más de 50 caracteres")
    @Column(unique = true)
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;
    @Column
    @NotBlank(message = "El email no puede estar vacio")
    @Size( min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    private String password;
    @Column
    private UsuarioRole usuarioRole;

    public Usuario(Long id, String nombre, String email, String password, UsuarioRole usuarioRole) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    public Usuario(String nombre, String email, String password, UsuarioRole usuarioRole) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.usuarioRole = usuarioRole;
    }

    public Usuario() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority= new SimpleGrantedAuthority(usuarioRole.name());
        return Collections.singletonList(grantedAuthority);

    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

