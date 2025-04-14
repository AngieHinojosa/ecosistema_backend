package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class CuentaBancariaDTO {

    private Long cuentaId;
    private Long usuarioId;  // Almacena solo el ID del usuario
    private String cuentaTipo;
    private String cuentaNumero;
    private Double cuentaSaldo;
}
