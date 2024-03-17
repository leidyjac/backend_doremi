package com.doremi.booking.dto.entrada.usuario;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String nombre;
    private String username;
    private String email;
    private String password;
    
}
