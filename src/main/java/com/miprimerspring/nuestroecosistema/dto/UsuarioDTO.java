package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UsuarioDTO {

    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String contrasena;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private boolean esVendedor;
}

