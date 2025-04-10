package com.miprimerspring.nuestroecosistema.DTO;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class CarritoDTO {

    @NotNull
    private Long carritoId;

    @NotNull
    private Long usuarioId;  // Referencia solo al ID del usuario

    @NotNull
    private Long productoId; // Referencia solo al ID del producto

    @Min(1)
    private int cantidad; // Asegura que la cantidad sea al menos 1

    @NotNull
    private LocalDateTime agregadoEn; // Asegura que se proporcione una fecha de agregado

}