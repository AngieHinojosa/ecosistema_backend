package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LogsActividadMapper {

    public LogsActividadDTO toDTO(LogsActividad entity) {
        if (entity == null) {
            return null;
        }

        LogsActividadDTO dto = new LogsActividadDTO();
        dto.setLogId(entity.getLogId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getUsuarioId() : null);
        dto.setLogAccion(entity.getLogAccion());
        dto.setLogFecha(entity.getLogFecha());
        dto.setLogDetalles(entity.getLogDetalles());

        return dto;
    }

    public LogsActividad toEntity(LogsActividadDTO dto) {
        if (dto == null) {
            return null;
        }

        LogsActividad entity = new LogsActividad();
        entity.setLogId(dto.getLogId());

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        entity.setUsuario(usuario);

        entity.setLogAccion(dto.getLogAccion());
        entity.setLogFecha(dto.getLogFecha());
        entity.setLogDetalles(dto.getLogDetalles());

        return entity;
    }
}
