package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Mensaje;

import java.util.List;
import java.util.Optional;

public interface MensajeService {

    // Crear un mensaje
    Mensaje crearMensaje(Mensaje mensaje);

    // Obtener todos los mensajes
    List<Mensaje> obtenerTodosLosMensajes();

    // Obtener un mensaje por su id
    Optional<Mensaje> obtenerMensajePorId(Long id);

    // Eliminar un mensaje por su id
    void eliminarMensaje(Long id);
}
