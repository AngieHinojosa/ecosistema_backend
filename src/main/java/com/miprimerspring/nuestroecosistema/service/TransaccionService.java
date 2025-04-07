package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Transaccion;

import java.util.List;
import java.util.Optional;

public interface TransaccionService {

    List<Transaccion> obtenerTodasLasTransacciones();
    Transaccion obtenerTransaccionPorId(Long id);
    Transaccion crearTransaccion(Transaccion transaccion);
    Transaccion actualizarTransaccion(Long id, Transaccion transaccion);
    void eliminarTransaccion(Long id);

}
