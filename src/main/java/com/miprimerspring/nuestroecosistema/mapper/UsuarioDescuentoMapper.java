package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDescuentoMapper {

    public static UsuarioDescuentoDTO toDTO(UsuarioDescuento usuarioDescuento) {
        if (usuarioDescuento == null) {
            return null;
        }

        UsuarioDescuentoDTO dto = new UsuarioDescuentoDTO();
        dto.setUsuarioDescuentoId(usuarioDescuento.getUsuarioDescuentoId());
        dto.setUsuarioId(usuarioDescuento.getUsuario().getUsuarioId());
        dto.setDescuentoAplicadoId(usuarioDescuento.getDescuentoAplicado() != null ? usuarioDescuento.getDescuentoAplicado().getDescuentoAplicadoId() : null);
        dto.setDescuentoExternoId(usuarioDescuento.getDescuentosExternos() != null ? usuarioDescuento.getDescuentosExternos().getDescuentoExternoId() : null);

        return dto;
    }

    public static UsuarioDescuento toEntity(UsuarioDescuentoDTO dto) {
        if (dto == null) {
            return null;
        }

        UsuarioDescuento usuarioDescuento = new UsuarioDescuento();
        // Aquí necesitarás setear las entidades 'Usuario', 'DescuentoAplicado' y 'DescuentoExterno' si es necesario
        usuarioDescuento.setUsuarioDescuentoId(dto.getUsuarioDescuentoId());

        return usuarioDescuento;
    }
}