package com.miprimerspring.nuestroecosistema.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {

    private Long carritoId;
    private Long usuarioId;  // Referencia al ID del usuario
    private Long productoId;  // Referencia al ID del producto
    private Integer carritoCantidad;
    private LocalDateTime carritoAgregadoEn;
    private Integer cantidad;

}