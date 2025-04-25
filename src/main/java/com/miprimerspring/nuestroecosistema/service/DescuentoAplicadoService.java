package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;

import java.util.List;

public interface DescuentoAplicadoService {

    List<DescuentoAplicadoDTO> obtenerDescuentosAplicados();

    DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id);

    DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO);

    DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO);

    void eliminarDescuentoAplicado(Long id);
}