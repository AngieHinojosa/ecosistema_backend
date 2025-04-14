package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransaccionDTO {

    private Long transaccionId;
    private Long cuentaId;
    private Long transaccionOrigenId;
    private Long transaccionDestinoId;
    private Double transaccionMonto;
    private Timestamp transaccionFecha;
}
