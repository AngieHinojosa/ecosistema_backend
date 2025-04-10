package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.PagosDTO;
import com.miprimerspring.nuestroecosistema.model.Pagos;

import java.util.List;
import java.util.Optional;

public interface PagosService {

    // Crear un pago
    PagosDTO crearPago(PagosDTO pagoDTO);

    // Obtener todos los pagos
    List<PagosDTO> obtenerTodosLosPagos();

    // Obtener un pago por su id
    Optional<PagosDTO> obtenerPagoPorId(Long id);

    // Eliminar un pago por su id
    void eliminarPago(Long id);
}
