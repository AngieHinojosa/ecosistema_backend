package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;

import java.util.List;

public interface PedidoDetalleService {

    PedidoDetalleDTO crearPedidoDetalle(PedidoDetalleDTO pedidoDetalleDTO);
    PedidoDetalleDTO obtenerPedidoDetallePorId(Long id);
    List<PedidoDetalleDTO> obtenerDetallesPorPedido(Integer pedidoId);
    List<PedidoDetalleDTO> obtenerDetallesPorProducto(Integer productoId);
    List<PedidoDetalleDTO> obtenerTodosDetalles();
    PedidoDetalleDTO actualizarPedidoDetalle(Long id, PedidoDetalleDTO pedidoDetalleDTO);
    void eliminarPedidoDetalle(Long id);
}
