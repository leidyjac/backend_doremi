package com.doremi.booking.entity;

import java.util.Arrays;
import java.util.List;


public enum Role {
    ROLE_USER,
    ROLE_ADMIN,

    public static List<UsuarioRole> getAllRoles() {
        return Arrays.asList(UsuarioRole.values());
    }
}
