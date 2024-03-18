package com.doremi.booking.dto.salida.Usuario;

import com.doremi.booking.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioSalidaDTO {
    Integer id;
    String username;
    String email;
    String nombre;
    String apellido;
    Role role;
    @Override
    
    public String toString() {
        return "UsuarioSalidaDTO [id=" + id + ", username=" + username + ", email=" + email + ", nombre=" + nombre
                + ", apellido=" + apellido + ", role=" + role + "]";
    }
    
}
