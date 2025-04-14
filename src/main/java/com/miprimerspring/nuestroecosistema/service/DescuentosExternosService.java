package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;

import java.util.List;

public interface DescuentosExternosService {

    DescuentoExternoDTO crearDescuentoExterno(DescuentoExternoDTO descuentoExternoDTO);
    DescuentoExternoDTO obtenerDescuentoExternoPorId(Integer id);
    List<DescuentoExternoDTO> obtenerDescuentosActivos(Boolean descuentoActivo);
    List<DescuentoExternoDTO> obtenerTodosDescuentosExternos();
    DescuentoExternoDTO actualizarDescuentoExterno(Integer id, DescuentoExternoDTO descuentoExternoDTO);
    void eliminarDescuentoExterno(Integer id);
}
