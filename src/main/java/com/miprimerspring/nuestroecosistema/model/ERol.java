package com.miprimerspring.nuestroecosistema.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ERol {

    ROLE_USER,     // Rol de usuario regular
    ROLE_ADMIN,    // Rol de administrador
    ROLE_VENDEDOR; // Nuevo rol de vendedor

    @JsonCreator
    public static ERol fromString(String role) {
        switch (role.toUpperCase()) {
            case "USER":
                return ERol.ROLE_USER;
            case "ADMIN":
                return ERol.ROLE_ADMIN;
            case "VENDEDOR":
                return ERol.ROLE_VENDEDOR;
            default:
                throw new IllegalArgumentException("Unknown role: '" + role + "'. Valid roles are USER, ADMIN, and VENDEDOR.");
        }
    }

    @JsonValue
    public String toJson() {
        return this.name().substring(5).toLowerCase();  // Convierte a minúsculas y omite "ROLE_"
    }
}