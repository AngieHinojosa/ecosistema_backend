package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class PedidoDetalleDTO {

    private Long detalleId;
    private Long pedidoId;
    private Long productoId;
    private Double detallePrecioUnitario;
    private Integer detalleCantidad;
}
