package com.miprimerspring.nuestroecosistema.mapper;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import com.miprimerspring.nuestroecosistema.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class PedidoDetalleMapper {

    public PedidoDetalleDTO toDTO(PedidoDetalle entity) {
        if (entity == null) {
            return null;
        }

        PedidoDetalleDTO dto = new PedidoDetalleDTO();
        dto.setDetalleId(entity.getDetalleId());
        dto.setPedidoId(entity.getPedido() != null ? entity.getPedido().getPedidoId() : null);
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getProductoId() : null);
        dto.setDetallePrecioUnitario(entity.getDetallePrecioUnitario());
        dto.setDetalleCantidad(entity.getDetalleCantidad());

        return dto;
    }

    public PedidoDetalle toEntity(PedidoDetalleDTO dto) {
        if (dto == null) {
            return null;
        }

        PedidoDetalle entity = new PedidoDetalle();
        entity.setDetalleId(dto.getDetalleId());

        Pedido pedido = new Pedido();
        pedido.setPedidoId(dto.getPedidoId());
        entity.setPedido(pedido);

        Producto producto = new Producto();
        producto.setProductoId(dto.getProductoId());
        entity.setProducto(producto);

        entity.setDetallePrecioUnitario(dto.getDetallePrecioUnitario());
        entity.setDetalleCantidad(dto.getDetalleCantidad());

        return entity;
    }
}
