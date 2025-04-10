package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> obtenerTodosLosUsuarios();
    Optional<UsuarioDTO> obtenerUsuarioPorId(Long id);
    UsuarioDTO crearUsuario(Usuario usuario);
    UsuarioDTO actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);

}
