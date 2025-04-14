package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class DescuentoAplicadoMapper {

    // Convertir de entidad a DTO
    public DescuentoAplicadoDTO toDTO(DescuentoAplicado entity) {
        if (entity == null) {
            return null;
        }

        DescuentoAplicadoDTO dto = new DescuentoAplicadoDTO();
        dto.setDescuentoAplicadoId(entity.getDescuentoAplicadoId());

        // Mapear el Pedido DTO usando el ID del pedido
        if (entity.getPedido() != null) {
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setPedidoId(entity.getPedido().getPedidoId());
            // Aquí podrías mapear más campos si es necesario
            dto.setPedidoId(pedidoDTO.getPedidoId());
        }

        // Mapear el Descuento DTO usando el ID del descuento
        if (entity.getDescuentosExternos() != null) {
            DescuentoExternoDTO descuentosExternosDTO = new DescuentoExternoDTO();
            descuentosExternosDTO.setDescuentoId((long) entity.getDescuentosExternos().getDescuentoId().intValue());  // Conversión
            dto.setDescuentoId(descuentosExternosDTO.getDescuentoId());
        }

        dto.setDescuentoMonto(entity.getDescuentoMonto());
        dto.setDescuentoCodigo(entity.getDescuentoCodigo());

        return dto;
    }

    // Convertir de DTO a entidad
    public DescuentoAplicado toEntity(DescuentoAplicadoDTO dto) {
        if (dto == null) {
            return null;
        }

        DescuentoAplicado entity = new DescuentoAplicado();
        entity.setDescuentoAplicadoId(dto.getDescuentoAplicadoId());

        // Mapear Pedido desde el DTO a la entidad
        Pedido pedido = new Pedido();
        pedido.setPedidoId(dto.getPedidoId()); // Establecer solo el ID
        entity.setPedido(pedido);

        // Mapear Descuento desde el DTO a la entidad
        DescuentosExternos descuento = new DescuentosExternos();
        descuento.setDescuentoId(Math.toIntExact(dto.getDescuentoId())); // Establecer solo el ID
        entity.setDescuentosExternos(descuento);

        entity.setDescuentoMonto(dto.getDescuentoMonto());
        entity.setDescuentoCodigo(dto.getDescuentoCodigo());

        return entity;
    }
}