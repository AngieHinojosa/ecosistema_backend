package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido entity) {
        if (entity == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();
        dto.setPedidoId(entity.getPedidoId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getUsuarioId() : null);
        dto.setDireccionId(entity.getDireccion() != null ? entity.getDireccion().getDireccionId() : null);
        dto.setPedidoTotal(entity.getPedidoTotal());
        dto.setPedidoMoneda(entity.getPedidoMoneda());
        dto.setPedidoEstado(entity.getPedidoEstado());
        dto.setPedidoMetodoPago(entity.getPedidoMetodoPago());
        dto.setPedidoUuidPago(entity.getPedidoUuidPago());
        dto.setPedidoCreadoEn(entity.getPedidoCreadoEn());
        dto.setPedidoFecha(entity.getPedidoFecha());

        return dto;
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pedido entity = new Pedido();
        entity.setPedidoId(dto.getPedidoId());

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(dto.getUsuarioId());
        entity.setUsuario(usuario);

        Direccion direccion = new Direccion();
        direccion.setDireccionId(dto.getDireccionId());
        entity.setDireccion(direccion);

        entity.setPedidoTotal(dto.getPedidoTotal());
        entity.setPedidoMoneda(dto.getPedidoMoneda());
        entity.setPedidoEstado(dto.getPedidoEstado());
        entity.setPedidoMetodoPago(dto.getPedidoMetodoPago());
        entity.setPedidoUuidPago(dto.getPedidoUuidPago());
        entity.setPedidoCreadoEn(dto.getPedidoCreadoEn());
        entity.setPedidoFecha(dto.getPedidoFecha());

        return entity;
    }

    // ✅ NUEVO MÉTODO para listas
    public List<PedidoDTO> toDTO(List<Pedido> pedidos) {
        if (pedidos == null) {
            return null;
        }
        return pedidos.stream().map(this::toDTO).toList();
    }
}