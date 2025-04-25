package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PagoDTO {

    private Long pagoId;
    private Long pedidoId;
    private Long cuentaId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private Double pagoMonto;


    private String pagoMetodo;
    private java.sql.Timestamp pagoFecha;
}
