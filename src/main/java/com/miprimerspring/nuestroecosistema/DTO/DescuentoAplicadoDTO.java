package com.miprimerspring.nuestroecosistema.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class DescuentoAplicadoDTO {

    private Long descuentoAplicadoId;

    @NotNull(message = "El monto del descuento no puede ser nulo.")
    @Positive(message = "El monto del descuento debe ser positivo.")
    private Double descuentoMonto;

    @NotBlank(message = "El código del descuento no puede estar vacío.")
    @Size(min = 3, max = 20, message = "El código del descuento debe tener entre 3 y 20 caracteres.")
    private String descuentoCodigo;

    @NotNull(message = "El ID del pedido no puede ser nulo.")
    private Long pedidoId;

    @NotNull(message = "El ID del descuento no puede ser nulo.")
    private Long descuentoId;
}
