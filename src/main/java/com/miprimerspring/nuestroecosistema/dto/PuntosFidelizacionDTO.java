package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PuntosFidelizacionDTO {

    private Long puntoId;
    private Long usuarioId;
    private Integer puntoCantidad;
    private Timestamp puntoUltimaActualizacion;
}
