package com.doremi.booking.entity;

import java.util.Arrays;
import java.util.List;


public enum UsuarioRole {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_ANONYMOUS;

    public static List<UsuarioRole> getAllRoles() {
        return Arrays.asList(UsuarioRole.values());
    }
}
