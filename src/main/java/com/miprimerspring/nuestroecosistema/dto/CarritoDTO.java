package com.miprimerspring.nuestroecosistema.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {

    private Long carritoId;
    private Long usuarioId;
    private List<CarritoProductoDTO> productos;
    private LocalDateTime carritoAgregadoEn;
}