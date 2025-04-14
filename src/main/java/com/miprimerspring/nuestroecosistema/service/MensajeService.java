package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.MensajeDTO;

import java.util.List;

public interface MensajeService {

    MensajeDTO crearMensaje(MensajeDTO mensajeDTO);
    MensajeDTO obtenerMensajePorId(Long id);
    List<MensajeDTO> obtenerMensajesPorEmisorId(Integer emisorId);
    List<MensajeDTO> obtenerMensajesPorReceptorId(Integer receptorId);
    List<MensajeDTO> obtenerMensajesPorLeido(Boolean mensajeLeido);
    List<MensajeDTO> obtenerTodosMensajes();
    MensajeDTO actualizarMensaje(Long id, MensajeDTO mensajeDTO);
    void eliminarMensaje(Long id);

}
