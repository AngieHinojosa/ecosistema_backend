package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class PagoDTO {

    private Long pagoId;
    private Long pedidoId;
    private Long cuentaId;
    private Double pagoMonto;
    private String pagoMetodo;
    private java.sql.Timestamp pagoFecha;
}
