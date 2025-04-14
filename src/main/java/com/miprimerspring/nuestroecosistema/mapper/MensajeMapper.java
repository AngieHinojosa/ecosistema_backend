package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;
import com.miprimerspring.nuestroecosistema.model.Mensaje;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MensajeMapper {

    public MensajeDTO toDTO(Mensaje entity) {
        if (entity == null) {
            return null;
        }

        MensajeDTO dto = new MensajeDTO();
        dto.setMensajeId(entity.getMensajeId());
        dto.setEmisorId(entity.getEmisor() != null ? entity.getEmisor().getUsuarioId() : null);
        dto.setReceptorId(entity.getReceptor() != null ? entity.getReceptor().getUsuarioId() : null);
        dto.setMensajeContenido(entity.getMensajeContenido());
        dto.setMensajeLeido(entity.getMensajeLeido());
        dto.setMensajeEnviadoEn(entity.getMensajeEnviadoEn());

        return dto;
    }

    public Mensaje toEntity(MensajeDTO dto) {
        if (dto == null) {
            return null;
        }

        Mensaje entity = new Mensaje();
        entity.setMensajeId(dto.getMensajeId());

        Usuario emisor = new Usuario();
        emisor.setUsuarioId(dto.getEmisorId());
        entity.setEmisor(emisor);

        Usuario receptor = new Usuario();
        receptor.setUsuarioId(dto.getReceptorId());
        entity.setReceptor(receptor);

        entity.setMensajeContenido(dto.getMensajeContenido());
        entity.setMensajeLeido(dto.getMensajeLeido());
        entity.setMensajeEnviadoEn(dto.getMensajeEnviadoEn());

        return entity;
    }
}
