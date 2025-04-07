package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;

import java.util.List;

public interface PedidoDetalleService {

    List<PedidoDetalle> obtenerTodosLosDetalles();
    PedidoDetalle obtenerDetallePorId(Long id);
    PedidoDetalle crearDetalle(PedidoDetalle pedidoDetalle);
    void eliminarDetalle(Long id);
}
