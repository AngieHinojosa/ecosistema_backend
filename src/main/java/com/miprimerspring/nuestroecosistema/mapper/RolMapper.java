package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.RolDTO;
import com.miprimerspring.nuestroecosistema.model.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {

    public RolDTO toDTO(Rol entity) {
        if (entity == null) {
            return null;
        }

        RolDTO dto = new RolDTO();
        dto.setRolId(entity.getRolId());
        dto.setRolNombre(entity.getRolNombre());

        return dto;
    }

    public Rol toEntity(RolDTO dto) {
        if (dto == null) {
            return null;
        }

        Rol entity = new Rol();
        entity.setRolId(dto.getRolId());
        entity.setRolNombre(dto.getRolNombre());

        return entity;
    }
}
