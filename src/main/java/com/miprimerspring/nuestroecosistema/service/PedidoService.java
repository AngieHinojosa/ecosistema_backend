package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> obtenerTodosLosPedidos();

    Pedido obtenerPedidoPorId(Long id);

    Pedido crearPedido(Pedido pedido);

    void eliminarPedido(Long id);
}
