package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;

import java.util.List;

public interface DescuentosExternosService {

    List<DescuentoExternoDTO> obtenerDescuentosExternos();

    DescuentoExternoDTO obtenerDescuentoExternoPorId(Long id);

    DescuentoExternoDTO crearDescuentoExterno(DescuentoExternoDTO descuentoExternoDTO);

    DescuentoExternoDTO actualizarDescuentoExterno(Long id, DescuentoExternoDTO descuentoExternoDTO);

    void eliminarDescuentoExterno(Long id);
}
