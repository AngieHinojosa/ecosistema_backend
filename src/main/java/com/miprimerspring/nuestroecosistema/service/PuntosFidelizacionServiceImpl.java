package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.mapper.PuntosFidelizacionMapper;
import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.repository.PuntosFidelizacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PuntosFidelizacionServiceImpl implements PuntosFidelizacionService {

    private final PuntosFidelizacionRepository puntosFidelizacionRepository;
    private final PuntosFidelizacionMapper puntosFidelizacionMapper;

    @Autowired
    public PuntosFidelizacionServiceImpl(PuntosFidelizacionRepository puntosFidelizacionRepository, PuntosFidelizacionMapper puntosFidelizacionMapper) {
        this.puntosFidelizacionRepository = puntosFidelizacionRepository;
        this.puntosFidelizacionMapper = puntosFidelizacionMapper;
    }

    @Override
    public PuntosFidelizacionDTO crearPuntoFidelizacion(PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacion puntosFidelizacion = puntosFidelizacionMapper.toEntity(puntosFidelizacionDTO);
        puntosFidelizacion = puntosFidelizacionRepository.save(puntosFidelizacion);
        return puntosFidelizacionMapper.toDTO(puntosFidelizacion);
    }

    @Override
    public PuntosFidelizacionDTO obtenerPuntoFidelizacionPorId(Long id) {
        return puntosFidelizacionRepository.findById(id)
                .map(puntosFidelizacionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<PuntosFidelizacionDTO> obtenerPuntosPorUsuario(Integer usuarioId) {
        List<PuntosFidelizacion> puntos = puntosFidelizacionRepository.findByUsuario_UsuarioId(usuarioId);
        return puntos.stream().map(puntosFidelizacionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<PuntosFidelizacionDTO> obtenerTodosPuntosFidelizacion() {
        List<PuntosFidelizacion> puntos = puntosFidelizacionRepository.findAll();
        return puntos.stream().map(puntosFidelizacionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PuntosFidelizacionDTO actualizarPuntoFidelizacion(Long id, PuntosFidelizacionDTO puntosFidelizacionDTO) {
        PuntosFidelizacion puntosFidelizacion = puntosFidelizacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Punto de fidelización no encontrado"));
        puntosFidelizacionMapper.toEntity(puntosFidelizacionDTO);  // Mapea DTO a entidad y actualiza
        puntosFidelizacion = puntosFidelizacionRepository.save(puntosFidelizacion);
        return puntosFidelizacionMapper.toDTO(puntosFidelizacion);
    }

    @Override
    public void eliminarPuntoFidelizacion(Long id) {
        puntosFidelizacionRepository.deleteById(id);
    }
}