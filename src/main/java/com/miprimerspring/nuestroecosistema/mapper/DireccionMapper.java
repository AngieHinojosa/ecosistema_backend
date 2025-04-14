package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public DireccionDTO toDTO(Direccion entity) {
        if (entity == null) {
            return null;
        }

        DireccionDTO dto = new DireccionDTO();
        dto.setDireccionId(entity.getDireccionId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getUsuarioId() : null);
        dto.setDireccionTipo(entity.getDireccionTipo());
        dto.setDireccionCalle(entity.getDireccionCalle());
        dto.setDireccionNumero(entity.getDireccionNumero());
        dto.setDireccionComuna(entity.getDireccionComuna());
        dto.setDireccionCiudad(entity.getDireccionCiudad());
        dto.setDireccionRegion(entity.getDireccionRegion());
        dto.setDireccionCodigoPostal(entity.getDireccionCodigoPostal());
        dto.setDireccionPais(entity.getDireccionPais());
        dto.setDireccionActiva(entity.getDireccionActiva());
        dto.setDireccionFechaCreacion(entity.getDireccionFechaCreacion());

        return dto;
    }

    public Direccion toEntity(DireccionDTO dto) {
        if (dto == null) {
            return null;
        }

        Direccion entity = new Direccion();
        entity.setDireccionId(dto.getDireccionId());

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        entity.setUsuario(usuario);

        entity.setDireccionTipo(dto.getDireccionTipo());
        entity.setDireccionCalle(dto.getDireccionCalle());
        entity.setDireccionNumero(dto.getDireccionNumero());
        entity.setDireccionComuna(dto.getDireccionComuna());
        entity.setDireccionCiudad(dto.getDireccionCiudad());
        entity.setDireccionRegion(dto.getDireccionRegion());
        entity.setDireccionCodigoPostal(dto.getDireccionCodigoPostal());
        entity.setDireccionPais(dto.getDireccionPais());
        entity.setDireccionActiva(dto.getDireccionActiva());
        entity.setDireccionFechaCreacion(dto.getDireccionFechaCreacion());

        return entity;
    }
}