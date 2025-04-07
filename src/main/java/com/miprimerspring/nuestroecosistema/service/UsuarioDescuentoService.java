package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;

import java.util.List;

public interface UsuarioDescuentoService {

    List<UsuarioDescuento> obtenerTodosLosUsuarioDescuentos();
    UsuarioDescuento obtenerUsuarioDescuentoPorId(Long id);
    UsuarioDescuento crearUsuarioDescuento(UsuarioDescuento usuarioDescuento);
    UsuarioDescuento actualizarUsuarioDescuento(Long id, UsuarioDescuento usuarioDescuento);
    void eliminarUsuarioDescuento(Long id);
}
