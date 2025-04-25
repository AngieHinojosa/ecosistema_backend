package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDescuentoDTO {

    private Long usuarioDescuentoId;
    private Long usuarioId;
    private Long descuentoAplicadoId;
    private Long descuentoExternoId;
}
