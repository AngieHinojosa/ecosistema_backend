package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> obtenerTodosLosRoles();
    Rol obtenerRolPorId(Long id);
    Rol crearRol(Rol rol);
    Rol actualizarRol(Long id, Rol rol);
    void eliminarRol(Long id);
}
