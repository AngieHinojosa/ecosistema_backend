package com.miprimerspring.nuestroecosistema.DTO;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class PagosDTO {

    private Long pagoId;
    private Double pagoMonto;
    private String pagoMetodo;
    private Timestamp pagoFecha;

    public PagosDTO(Long pagoId, Double pagoMonto, String pagoMetodo, Timestamp pagoFecha) {
        this.pagoId = pagoId;
        this.pagoMonto = pagoMonto;
        this.pagoMetodo = pagoMetodo;
        this.pagoFecha = pagoFecha;
    }
}
