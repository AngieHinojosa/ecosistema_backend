package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Pagos;

import java.util.List;
import java.util.Optional;

public interface PagosService {

    // Crear un pago
    Pagos crearPago(Pagos pago);

    // Obtener todos los pagos
    List<Pagos> obtenerTodosLosPagos();

    // Obtener un pago por su id
    Optional<Pagos> obtenerPagoPorId(Long id);

    // Eliminar un pago por su id
    void eliminarPago(Long id);
}
