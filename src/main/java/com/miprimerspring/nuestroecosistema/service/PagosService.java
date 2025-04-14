package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;

import java.util.List;
import java.util.Optional;

public interface PagosService {

    PagoDTO crearPago(PagoDTO pagoDTO);
    PagoDTO obtenerPagoPorId(Long id);
    List<PagoDTO> obtenerPagosPorPedidoId(Integer pedidoId);
    List<PagoDTO> obtenerPagosPorCuentaId(Integer cuentaId);
    List<PagoDTO> obtenerPagosPorMetodo(String metodo);
    List<PagoDTO> obtenerTodosPagos();
    PagoDTO actualizarPago(Long id, PagoDTO pagoDTO);
    void eliminarPago(Long id);
}
