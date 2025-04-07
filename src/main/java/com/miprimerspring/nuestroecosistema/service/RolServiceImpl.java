package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerRolPorId(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        return rol.orElse(null);
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizarRol(Long id, Rol rol) {
        if (rolRepository.existsById(id)) {
            rol.setRol_id(id);
            return rolRepository.save(rol);
        }
        return null;
    }

    @Override
    public void eliminarRol(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
    }
}
