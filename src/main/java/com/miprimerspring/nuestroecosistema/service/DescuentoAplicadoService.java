package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;

import java.util.List;

public interface DescuentoAplicadoService {

    // Método para obtener todos los descuentos aplicados
    List<DescuentoAplicadoDTO> obtenerTodosLosDescuentosAplicados();

    // Método para obtener un descuento aplicado por su ID
    DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id);

    // Método para crear un nuevo descuento aplicado
    DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO);

    // Método para actualizar un descuento aplicado
    DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO);

    // Método para eliminar un descuento aplicado
    void eliminarDescuentoAplicado(Long id);
}
