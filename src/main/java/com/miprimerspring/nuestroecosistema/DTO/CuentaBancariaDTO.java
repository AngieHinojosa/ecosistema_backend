package com.miprimerspring.nuestroecosistema.DTO;

import lombok.Data;

@Data
public class CuentaBancariaDTO {

    private long cuentaId;
    private String cuentaTipo;
    private double cuentaSaldo;
    private long usuarioId;  // ID del usuario relacionado

    // Constructor para convertir entidad a DTO
    public CuentaBancariaDTO(long cuentaId, String cuentaTipo, double cuentaSaldo, long usuarioId) {
        this.cuentaId = cuentaId;
        this.cuentaTipo = cuentaTipo;
        this.cuentaSaldo = cuentaSaldo;
        this.usuarioId = usuarioId;
    }
}
