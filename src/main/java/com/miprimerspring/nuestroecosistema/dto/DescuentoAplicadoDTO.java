package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class DescuentoAplicadoDTO {

    private Long descuentoAplicadoId;
    private Long pedidoId;  // Almacena solo el ID del pedido
    private Long descuentoId;  // Almacena solo el ID del descuento
    private Double descuentoMonto;
    private String descuentoCodigo;
}
