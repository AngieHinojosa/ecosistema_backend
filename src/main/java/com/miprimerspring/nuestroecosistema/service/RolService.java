package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    Rol saveRol(Rol rol);
    List<Rol> getAllRoles();
    Optional<Rol> getRolById(int id);
    void deleteRol(int id);
}
