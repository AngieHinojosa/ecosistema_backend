package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class TarjetaDTO {

    private Long tarjetaId;
    private Long usuarioId;
    private String tarjetaTipo;
    private String tarjetaNumero;
    private String tarjetaExpiracion;
    private String tarjetaCvv;
    private String tarjetaEstado;
}
