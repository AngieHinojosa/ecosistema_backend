package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.RolDTO;
import com.miprimerspring.nuestroecosistema.mapper.RolMapper;
import com.miprimerspring.nuestroecosistema.model.Rol;
import com.miprimerspring.nuestroecosistema.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    @Override
    public RolDTO crearRol(RolDTO rolDTO) {
        Rol rol = rolMapper.toEntity(rolDTO);
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    public RolDTO obtenerRolPorId(Integer id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<RolDTO> obtenerTodosRoles() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream().map(rolMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RolDTO actualizarRol(Integer id, RolDTO rolDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado"));
        rol.setRolNombre(rolDTO.getRolNombre());
        rol = rolRepository.save(rol);
        return rolMapper.toDTO(rol);
    }

    @Override
    public void eliminarRol(Integer id) {
        rolRepository.deleteById(id);
    }

    @Override
    public RolDTO obtenerRolPorNombre(String rolNombre) {
        Rol rol = rolRepository.findByRolNombre(rolNombre);
        return rol != null ? rolMapper.toDTO(rol) : null;
    }
}