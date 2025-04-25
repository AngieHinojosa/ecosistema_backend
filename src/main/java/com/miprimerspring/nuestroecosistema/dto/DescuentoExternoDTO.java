package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DescuentoExternoDTO {

    private Long descuentoExternoId;
    private String empresa;
    private String descripcion;
    private Double porcentajeDescuento;
    private String codigoDescuento;
}