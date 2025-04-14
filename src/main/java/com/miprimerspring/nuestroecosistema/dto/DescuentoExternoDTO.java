package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class DescuentoExternoDTO {

    private Long descuentoId;
    private String empresaNombre;
    private Double descuentoPorcentaje;
    private String descuentoCodigo;
    private byte[] descuentoVigenciaInicio;
    private byte[] descuentoVigenciaFin;
    private String descuentoMetodoPago;
    private String descuentoBanco;
    private Boolean descuentoActivo;
}
