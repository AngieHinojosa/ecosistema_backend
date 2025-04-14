package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class PedidoDTO {

    private Long pedidoId;
    private Long usuarioId;
    private Long direccionId;
    private BigDecimal pedidoTotal;
    private String pedidoMoneda;
    private String pedidoEstado;
    private String pedidoMetodoPago;
    private String pedidoUuidPago;
    private Timestamp pedidoCreadoEn;
    private Date pedidoFecha;
}
