package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class MensajeDTO {

    private Long mensajeId;
    private Long emisorId;
    private Long receptorId;
    private String mensajeContenido;
    private Boolean mensajeLeido;
    private java.sql.Timestamp mensajeEnviadoEn;
}