package com.miprimerspring.nuestroecosistema.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long usuarioId;
    private String usuarioUuid;
    private String usuarioNombres;
    private String usuarioApellidos;
    private String usuarioCorreo;
    private String usuarioTelefono;
    private String usuarioContrasena; // Cambié 'usuarioContrasenia' por 'usuarioContrasena'
    private String usuarioEstado;
    private Boolean usuarioVendedor;
    private Long rolId; // Está bien tener esto para la asignación de Rol
    private String usuarioTipoDocumento;
    private String usuarioNumeroDocumento;
    private String usuarioFechaNacimiento;
}