package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CuentaBancariaDTO {

    private Long cuentaId;
    private Long usuarioId;
    private String cuentaTipo = "debito";
    private String cuentaNumero;
    private BigDecimal cuentaSaldo = BigDecimal.ZERO;
    private String fechaCreacion;
}
