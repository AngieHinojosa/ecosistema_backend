package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;

import java.util.List;

public interface DescuentosExternosService {

    // Método para obtener todos los descuentos externos
    List<DescuentosExternos> obtenerTodosLosDescuentosExternos();

    // Método para obtener un descuento externo por su ID
    DescuentosExternos obtenerDescuentoExternoPorId(Long id);

    // Método para crear un nuevo descuento externo
    DescuentosExternos crearDescuentoExterno(DescuentosExternos descuentosExternos);

    // Método para actualizar un descuento externo
    DescuentosExternos actualizarDescuentoExterno(Long id, DescuentosExternos descuentosExternos);

    // Método para eliminar un descuento externo
    void eliminarDescuentoExterno(Long id);
}
