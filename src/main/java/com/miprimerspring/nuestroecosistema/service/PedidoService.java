package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;

import java.sql.Date;
import java.util.List;

public interface PedidoService {

    PedidoDTO crearPedido(PedidoDTO pedidoDTO);

    PedidoDTO obtenerPedidoPorId(Long id);

    List<PedidoDTO> obtenerPedidosPorUsuario(Integer usuarioId);

    List<PedidoDTO> obtenerPedidosPorDireccion(Integer direccionId);

    List<PedidoDTO> obtenerPedidosPorEstado(String estado);

    List<PedidoDTO> obtenerPedidosPorFecha(Date fecha);

    List<PedidoDTO> obtenerTodosPedidos();

    PedidoDTO actualizarPedido(Long id, PedidoDTO pedidoDTO);

    void eliminarPedido(Long id);
}
