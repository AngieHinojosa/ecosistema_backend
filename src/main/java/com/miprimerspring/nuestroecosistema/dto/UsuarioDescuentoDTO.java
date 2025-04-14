package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class UsuarioDescuentoDTO {

    private Long usuarioDescuentoId;
    private Long descuentoId;
    private Long usuarioId;
    private Boolean descuentoAplicado;
}
