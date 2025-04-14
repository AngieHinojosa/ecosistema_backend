package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class DireccionDTO {

    private Long direccionId;
    private Long usuarioId;  // Almacena solo el ID del usuario
    private String direccionTipo;
    private String direccionCalle;
    private String direccionNumero;
    private String direccionComuna;
    private String direccionCiudad;
    private String direccionRegion;
    private String direccionCodigoPostal;
    private String direccionPais;
    private Boolean direccionActiva;
    private java.sql.Timestamp direccionFechaCreacion;
}
