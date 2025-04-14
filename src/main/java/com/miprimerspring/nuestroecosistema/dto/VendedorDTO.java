package com.miprimerspring.nuestroecosistema.dto;

import lombok.Data;

@Data
public class VendedorDTO {

    private Long vendedorId;
    private Long usuarioId;
    private String vendedorNombreTienda;
    private String vendedorDescripcion;
    private String vendedorLogoUrl;
    private String vendedorFechaCreacion;
    private String vendedorEstado;
}
