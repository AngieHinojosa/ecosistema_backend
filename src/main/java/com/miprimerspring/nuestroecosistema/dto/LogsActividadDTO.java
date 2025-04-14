package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class LogsActividadDTO {

    private Long logId;
    private Long usuarioId;  // Almacena solo el ID del usuario
    private String logAccion;
    private java.sql.Timestamp logFecha;
    private String logDetalles;
}
