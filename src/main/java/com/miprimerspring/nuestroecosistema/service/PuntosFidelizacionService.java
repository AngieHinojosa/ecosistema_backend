package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;

import java.util.List;

public interface PuntosFidelizacionService {

    List<PuntosFidelizacion> obtenerTodosLosPuntos();
    PuntosFidelizacion obtenerPuntosPorId(Long id);
    PuntosFidelizacion crearPuntos(PuntosFidelizacion puntosFidelizacion);
    PuntosFidelizacion actualizarPuntos(Long id, PuntosFidelizacion puntosFidelizacion);
    void eliminarPuntos(Long id);
}
