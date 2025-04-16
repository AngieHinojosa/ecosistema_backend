package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "El correo electrónico es obligatorio y no puede estar vacío.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    private String usuarioCorreo;

    @NotBlank(message = "La contraseña es obligatoria y no puede estar vacía.")
    private String usuarioContrasena;
}
