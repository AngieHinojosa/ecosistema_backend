package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Direccion;

import java.util.List;

public interface DireccionService {

    // Método para obtener todas las direcciones
    List<Direccion> obtenerTodasLasDirecciones();

    // Método para obtener una dirección por su ID
    Direccion obtenerDireccionPorId(Long id);

    // Método para crear una nueva dirección
    Direccion crearDireccion(Direccion direccion);

    // Método para actualizar una dirección
    Direccion actualizarDireccion(Long id, Direccion direccion);

    // Método para eliminar una dirección
    void eliminarDireccion(Long id);
}
