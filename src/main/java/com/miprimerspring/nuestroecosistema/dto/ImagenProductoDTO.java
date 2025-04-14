package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class ImagenProductoDTO {

    private Long imagenId;
    private Long productoId;  // Almacena solo el ID del producto
    private String imagenUrl;
    private Boolean imagenEsPrincipal;
}
