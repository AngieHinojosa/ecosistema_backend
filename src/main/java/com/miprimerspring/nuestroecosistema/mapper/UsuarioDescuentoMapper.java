package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDescuentoMapper {

    // Método para mapear de DTO a entidad
    public UsuarioDescuento toEntity(UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuento usuarioDescuento = new UsuarioDescuento();
        usuarioDescuento.setUsuarioDescuentoId(usuarioDescuentoDTO.getUsuarioDescuentoId());
        usuarioDescuento.setDescuentoAplicado(usuarioDescuentoDTO.getDescuentoAplicado());  // Mapeo del descuentoAplicado
        // Aquí solo se mapea la información básica de la entidad
        return usuarioDescuento;
    }

    // Método para mapear de entidad a DTO
    public UsuarioDescuentoDTO toDTO(UsuarioDescuento usuarioDescuento) {
        UsuarioDescuentoDTO usuarioDescuentoDTO = new UsuarioDescuentoDTO();
        usuarioDescuentoDTO.setUsuarioDescuentoId(usuarioDescuento.getUsuarioDescuentoId());
        usuarioDescuentoDTO.setDescuentoAplicado(usuarioDescuento.getDescuentoAplicado());  // Mapeo del descuentoAplicado
        return usuarioDescuentoDTO;
    }
}