package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.mapper.DireccionMapper;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.repository.DireccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {

    private final DireccionRepository direccionRepository;
    private final DireccionMapper direccionMapper;

    @Autowired
    public DireccionServiceImpl(DireccionRepository direccionRepository, DireccionMapper direccionMapper) {
        this.direccionRepository = direccionRepository;
        this.direccionMapper = direccionMapper;
    }

    @Override
    public DireccionDTO crearDireccion(DireccionDTO direccionDTO) {
        Direccion direccion = direccionMapper.toEntity(direccionDTO);
        Direccion savedDireccion = direccionRepository.save(direccion);
        return direccionMapper.toDTO(savedDireccion);
    }

    @Override
    public DireccionDTO obtenerDireccionPorId(Integer id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
        return direccionMapper.toDTO(direccion);
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorUsuarioId(Integer usuarioId) {
        List<Direccion> direcciones = direccionRepository.findByUsuario_UsuarioId(usuarioId);
        return direcciones.stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesActivas(Boolean direccionActiva) {
        List<Direccion> direcciones = direccionRepository.findByDireccionActiva(direccionActiva);
        return direcciones.stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> obtenerTodasDirecciones() {
        List<Direccion> direcciones = direccionRepository.findAll();
        return direcciones.stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DireccionDTO actualizarDireccion(Integer id, DireccionDTO direccionDTO) {
        Direccion direccionExistente = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
        direccionExistente = direccionMapper.toEntity(direccionDTO);
        direccionExistente.setDireccionId(Long.valueOf(id));  // Mantener el ID
        Direccion updatedDireccion = direccionRepository.save(direccionExistente);
        return direccionMapper.toDTO(updatedDireccion);
    }

    @Override
    public void eliminarDireccion(Integer id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
        direccionRepository.delete(direccion);
    }
}