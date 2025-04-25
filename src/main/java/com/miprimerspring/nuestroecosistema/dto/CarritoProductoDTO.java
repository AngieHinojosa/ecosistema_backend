package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarritoProductoDTO {

    private Long id; // <- este campo es necesario para que setId() funcione
    private Long productoId;
    private Integer cantidad;
    private LocalDateTime agregadoEn;
}
