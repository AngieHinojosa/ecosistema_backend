package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.MensajeDTO;

import java.util.List;

public interface MensajeService {

    List<MensajeDTO> obtenerTodosLosMensajes();

    MensajeDTO obtenerMensajePorId(Long id);

    MensajeDTO crearMensaje(MensajeDTO mensajeDTO);

    void eliminarMensaje(Long id);

}
