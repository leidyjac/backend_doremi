package com.doremi.booking.dto.entrada.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "El email no puede ser nulo")
    @NotBlank(message = "Debe especificarse el email")
    @Email(message = "Debe tener formato de email")
    String username;
    @NotNull(message = "la clave no puede ser nula")
    @NotBlank(message = "Debe especificarse la clave del usuario")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    String password;
    @NotNull(message = "El nombre del Usuario no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del usuario")
    String nombre; 
    @NotNull(message = "El apellido del Usuario no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del Usuario")
    String apellido; 
}
