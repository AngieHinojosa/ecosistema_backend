package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;

import java.util.List;

public interface PuntosFidelizacionService {

    PuntosFidelizacionDTO crearPuntoFidelizacion(PuntosFidelizacionDTO puntosFidelizacionDTO);
    PuntosFidelizacionDTO obtenerPuntoFidelizacionPorId(Long id);
    List<PuntosFidelizacionDTO> obtenerPuntosPorUsuario(Integer usuarioId);
    List<PuntosFidelizacionDTO> obtenerTodosPuntosFidelizacion();
    PuntosFidelizacionDTO actualizarPuntoFidelizacion(Long id, PuntosFidelizacionDTO puntosFidelizacionDTO);
    void eliminarPuntoFidelizacion(Long id);
}
