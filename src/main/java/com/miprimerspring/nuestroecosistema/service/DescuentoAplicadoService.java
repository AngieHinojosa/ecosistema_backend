package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;

import java.util.List;

public interface DescuentoAplicadoService {

    // Método para obtener todos los descuentos aplicados
    List<DescuentoAplicado> obtenerTodosLosDescuentosAplicados();

    // Método para obtener un descuento aplicado por su ID
    DescuentoAplicado obtenerDescuentoAplicadoPorId(Long id);

    // Método para crear un nuevo descuento aplicado
    DescuentoAplicado crearDescuentoAplicado(DescuentoAplicado descuentoAplicado);

    // Método para actualizar un descuento aplicado
    DescuentoAplicado actualizarDescuentoAplicado(Long id, DescuentoAplicado descuentoAplicado);

    // Método para eliminar un descuento aplicado
    void eliminarDescuentoAplicado(Long id);
}
