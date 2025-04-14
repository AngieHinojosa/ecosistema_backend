package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.RolDTO;
import com.miprimerspring.nuestroecosistema.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    RolDTO crearRol(RolDTO rolDTO);
    RolDTO obtenerRolPorId(Integer id);
    List<RolDTO> obtenerTodosRoles();
    RolDTO actualizarRol(Integer id, RolDTO rolDTO);
    void eliminarRol(Integer id);
    RolDTO obtenerRolPorNombre(String rolNombre);
}
