package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;

import java.util.List;

public interface UsuarioDescuentoService {

    UsuarioDescuentoDTO crearUsuarioDescuento(UsuarioDescuentoDTO usuarioDescuentoDTO);
    UsuarioDescuentoDTO obtenerUsuarioDescuentoPorId(Long id);
    List<UsuarioDescuentoDTO> obtenerUsuarioDescuentosPorUsuarioId(Integer usuarioId);
    List<UsuarioDescuentoDTO> obtenerUsuarioDescuentosPorDescuentoId(Long descuentoId);
    UsuarioDescuentoDTO obtenerUsuarioDescuentoPorUsuarioYDescuento(Integer usuarioId, Long descuentoId);
    void eliminarUsuarioDescuento(Long id);
}
