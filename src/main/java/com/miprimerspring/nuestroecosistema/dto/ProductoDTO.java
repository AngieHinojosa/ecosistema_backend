package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductoDTO {

    private Long productoId;
    private Long vendedorId;
    private Long categoriaId;
    private String productoNombre;
    private String productoDescripcion;
    private Double productoPrecio;
    private Integer productoStock;
    private String productoEstado;
    private Timestamp productoCreadoEn;
}
