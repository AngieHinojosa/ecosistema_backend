package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class DescuentoAplicadoDTO {

    private Long descuentoAplicadoId;
    private Long pedidoId;
    private String descripcion;
    private Double porcentajeDescuento;
    private Double descuentoAplicadoTotal;
    private String descuentoCodigo;
}