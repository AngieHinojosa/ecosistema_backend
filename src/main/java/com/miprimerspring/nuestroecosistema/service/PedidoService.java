package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    Pedido savePedido(Pedido pedido);
    List<Pedido> getAllPedidos();
    Optional<Pedido> getPedidoById(int id);
    void deletePedido(int id);
}
