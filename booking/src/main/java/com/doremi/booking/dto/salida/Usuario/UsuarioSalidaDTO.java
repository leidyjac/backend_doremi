package com.doremi.booking.dto.salida.Usuario;

import com.doremi.booking.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioSalidaDTO {
    Long usuario_id;
    String username;
    String nombre;
    String apellido;
    Role role;
    @Override
    
    public String toString() {
        return "UsuarioSalidaDTO [id=" + usuario_id + ", username=" + username + ", nombre=" + nombre
                + ", apellido=" + apellido + ", role=" + role + "]";
    }
    
}
