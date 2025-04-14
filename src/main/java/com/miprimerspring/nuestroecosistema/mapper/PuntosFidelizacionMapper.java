package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PuntosFidelizacionMapper {

    public PuntosFidelizacion toEntity(PuntosFidelizacionDTO dto) {
        if (dto == null) {
            return null;
        }

        PuntosFidelizacion entity = new PuntosFidelizacion();
        entity.setPuntoId(dto.getPuntoId());
        entity.setPuntoCantidad(dto.getPuntoCantidad());
        entity.setPuntoUltimaActualizacion(dto.getPuntoUltimaActualizacion());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(dto.getUsuarioId());
            entity.setUsuario(usuario);
        }

        return entity;
    }

    public PuntosFidelizacionDTO toDTO(PuntosFidelizacion entity) {
        if (entity == null) {
            return null;
        }

        PuntosFidelizacionDTO dto = new PuntosFidelizacionDTO();
        dto.setPuntoId(entity.getPuntoId());
        dto.setPuntoCantidad(entity.getPuntoCantidad());
        dto.setPuntoUltimaActualizacion(entity.getPuntoUltimaActualizacion());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getUsuarioId() : null);
        return dto;
    }
}