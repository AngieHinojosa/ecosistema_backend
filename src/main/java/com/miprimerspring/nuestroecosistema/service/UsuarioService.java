package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);

    Usuario registrarDesdeDTO(UsuarioDTO dto); // NUEVO

    Optional<Usuario> obtenerUsuarioPorId(Long id);

    Optional<Usuario> obtenerUsuarioPorCorreo(String correo);

    boolean existeUsuarioPorCorreo(String correo);

    List<Usuario> obtenerUsuariosPorEstado(String estado);

    List<Usuario> obtenerUsuariosPorRol(ERol rol);

    List<Usuario> obtenerUsuariosPorTipoDocumento(String tipoDocumento);

    List<Usuario> obtenerUsuariosPorNumeroDocumento(String numeroDocumento);

    List<Usuario> obtenerUsuariosPorFechaNacimiento(LocalDate fechaNacimiento);

    void eliminarUsuario(Long id);
}