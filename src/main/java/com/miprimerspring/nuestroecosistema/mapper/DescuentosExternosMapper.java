package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import org.springframework.stereotype.Component;

@Component
public class DescuentosExternosMapper {

    public DescuentoExternoDTO toDTO(DescuentosExternos entity) {
        if (entity == null) {
            return null;
        }

        DescuentoExternoDTO dto = new DescuentoExternoDTO();
        dto.setDescuentoId(Long.valueOf(entity.getDescuentoId()));
        dto.setEmpresaNombre(entity.getEmpresaNombre());
        dto.setDescuentoPorcentaje(entity.getDescuentoPorcentaje());
        dto.setDescuentoCodigo(entity.getDescuentoCodigo());
        dto.setDescuentoVigenciaInicio(entity.getDescuentoVigenciaInicio());
        dto.setDescuentoVigenciaFin(entity.getDescuentoVigenciaFin());
        dto.setDescuentoMetodoPago(entity.getDescuentoMetodoPago());
        dto.setDescuentoBanco(entity.getDescuentoBanco());
        dto.setDescuentoActivo(entity.getDescuentoActivo());

        return dto;
    }

    public DescuentosExternos toEntity(DescuentoExternoDTO dto) {
        if (dto == null) {
            return null;
        }

        DescuentosExternos entity = new DescuentosExternos();
        entity.setDescuentoId(Math.toIntExact(dto.getDescuentoId()));
        entity.setEmpresaNombre(dto.getEmpresaNombre());
        entity.setDescuentoPorcentaje(dto.getDescuentoPorcentaje());
        entity.setDescuentoCodigo(dto.getDescuentoCodigo());
        entity.setDescuentoVigenciaInicio(dto.getDescuentoVigenciaInicio());
        entity.setDescuentoVigenciaFin(dto.getDescuentoVigenciaFin());
        entity.setDescuentoMetodoPago(dto.getDescuentoMetodoPago());
        entity.setDescuentoBanco(dto.getDescuentoBanco());
        entity.setDescuentoActivo(dto.getDescuentoActivo());

        return entity;
    }
}