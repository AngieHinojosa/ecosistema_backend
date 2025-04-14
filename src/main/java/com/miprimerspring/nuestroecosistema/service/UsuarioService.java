package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDTO;
import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuarioPorId(Long id);
    UsuarioDTO obtenerUsuarioPorCorreo(String correo);
    List<UsuarioDTO> obtenerUsuariosPorEstado(String estado);
    List<UsuarioDTO> obtenerUsuariosPorRolId(Integer rolId);
    List<UsuarioDTO> obtenerUsuariosPorVendedor(Boolean vendedor);
    List<UsuarioDTO> obtenerUsuariosPorTipoDocumento(String tipoDocumento);
    List<UsuarioDTO> obtenerUsuariosPorNumeroDocumento(String numeroDocumento);
    List<UsuarioDTO> obtenerUsuariosPorFechaNacimiento(String fechaNacimiento);
    void eliminarUsuario(Long id);

}
