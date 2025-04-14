package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.model.Direccion;

import java.util.List;

public interface DireccionService {

    DireccionDTO crearDireccion(DireccionDTO direccionDTO);
    DireccionDTO obtenerDireccionPorId(Integer id);
    List<DireccionDTO> obtenerDireccionesPorUsuarioId(Integer usuarioId);
    List<DireccionDTO> obtenerDireccionesActivas(Boolean direccionActiva);
    List<DireccionDTO> obtenerTodasDirecciones();
    DireccionDTO actualizarDireccion(Integer id, DireccionDTO direccionDTO);
    void eliminarDireccion(Integer id);
}
