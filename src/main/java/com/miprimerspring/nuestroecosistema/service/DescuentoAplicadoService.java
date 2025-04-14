package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;

import java.util.List;

public interface DescuentoAplicadoService {

    DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO);
    DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id);
    List<DescuentoAplicadoDTO> obtenerDescuentosPorPedido(Integer pedidoId);
    List<DescuentoAplicadoDTO> obtenerDescuentosPorDescuento(Integer descuentoId);
    List<DescuentoAplicadoDTO> obtenerTodosDescuentosAplicados();
    DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO);
    void eliminarDescuentoAplicado(Long id);
}
