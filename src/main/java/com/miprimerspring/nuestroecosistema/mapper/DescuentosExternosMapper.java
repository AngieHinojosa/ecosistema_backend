package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import org.springframework.stereotype.Component;

@Component
public class DescuentosExternosMapper {

    public static DescuentoExternoDTO toDTO(DescuentosExternos descuentoExterno) {
        if (descuentoExterno == null) {
            return null;
        }

        DescuentoExternoDTO dto = new DescuentoExternoDTO();
        dto.setDescuentoExternoId(descuentoExterno.getDescuentoExternoId());
        dto.setEmpresa(descuentoExterno.getEmpresa());
        dto.setDescripcion(descuentoExterno.getDescripcion());
        dto.setPorcentajeDescuento(descuentoExterno.getPorcentajeDescuento());
        dto.setCodigoDescuento(descuentoExterno.getCodigoDescuento());

        return dto;
    }

    public static DescuentosExternos toEntity(DescuentoExternoDTO dto) {
        if (dto == null) {
            return null;
        }

        DescuentosExternos descuento = new DescuentosExternos();
        descuento.setDescuentoExternoId(dto.getDescuentoExternoId());
        descuento.setEmpresa(dto.getEmpresa());
        descuento.setDescripcion(dto.getDescripcion());
        descuento.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        descuento.setCodigoDescuento(dto.getCodigoDescuento());

        return descuento;
    }
}