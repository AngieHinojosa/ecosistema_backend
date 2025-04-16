package com.miprimerspring.nuestroecosistema.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ERol {

    ROLE_USER,  // Rol de usuario regular
    ROLE_ADMIN; // Rol de administrador

    // Método para convertir una cadena a un valor del enum (por ejemplo, al recibir roles desde un request)
    @JsonCreator
    public static ERol fromString(String role) {
        switch (role.toUpperCase()) {
            case "USER":
                return ERol.ROLE_USER;
            case "ADMIN":
                return ERol.ROLE_ADMIN;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    // Método para convertir el enum a una cadena (para serialización a JSON)
    @JsonValue
    public String toJson() {
        return this.name();
    }
}