package com.doremi.booking.dto.entrada.modificacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.doremi.booking.entity.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioCambioRoleEntradaDTO {
    private String username;
    private Role role;
    


}
