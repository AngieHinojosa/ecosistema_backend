package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(int id);
    void deleteUsuario(int id);
}
