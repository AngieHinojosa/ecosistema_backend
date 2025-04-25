package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;

import java.util.List;

public interface UsuarioDescuentoService {

    List<UsuarioDescuentoDTO> obtenerUsuarioDescuentos();

    UsuarioDescuentoDTO obtenerUsuarioDescuentoPorId(Long id);

    UsuarioDescuentoDTO crearUsuarioDescuento(UsuarioDescuentoDTO usuarioDescuentoDTO);

    void eliminarUsuarioDescuento(Long id);
}
