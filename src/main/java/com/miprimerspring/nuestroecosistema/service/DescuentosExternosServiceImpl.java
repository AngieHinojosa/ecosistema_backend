package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.mapper.DescuentosExternosMapper;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.repository.DescuentosExternosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DescuentosExternosServiceImpl implements  DescuentosExternosService {

    private final DescuentosExternosRepository descuentosExternosRepository;
    private final DescuentosExternosMapper descuentosExternosMapper;

    @Autowired
    public DescuentosExternosServiceImpl(DescuentosExternosRepository descuentosExternosRepository, DescuentosExternosMapper descuentosExternosMapper) {
        this.descuentosExternosRepository = descuentosExternosRepository;
        this.descuentosExternosMapper = descuentosExternosMapper;
    }

    @Override
    public DescuentoExternoDTO crearDescuentoExterno(DescuentoExternoDTO descuentoExternoDTO) {
        DescuentosExternos descuentoExterno = descuentosExternosMapper.toEntity(descuentoExternoDTO);
        DescuentosExternos savedDescuentoExterno = descuentosExternosRepository.save(descuentoExterno);
        return descuentosExternosMapper.toDTO(savedDescuentoExterno);
    }

    @Override
    public DescuentoExternoDTO obtenerDescuentoExternoPorId(Integer id) {
        DescuentosExternos descuentoExterno = descuentosExternosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Externo no encontrado"));
        return descuentosExternosMapper.toDTO(descuentoExterno);
    }

    @Override
    public List<DescuentoExternoDTO> obtenerDescuentosActivos(Boolean descuentoActivo) {
        List<DescuentosExternos> descuentos = descuentosExternosRepository.findByDescuentoActivo(descuentoActivo);
        return descuentos.stream()
                .map(descuentosExternosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DescuentoExternoDTO> obtenerTodosDescuentosExternos() {
        List<DescuentosExternos> descuentos = descuentosExternosRepository.findAll();
        return descuentos.stream()
                .map(descuentosExternosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoExternoDTO actualizarDescuentoExterno(Integer id, DescuentoExternoDTO descuentoExternoDTO) {
        DescuentosExternos descuentoExistente = descuentosExternosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Externo no encontrado"));
        descuentoExistente = descuentosExternosMapper.toEntity(descuentoExternoDTO);
        descuentoExistente.setDescuentoId(id);  // Mantener el ID
        DescuentosExternos updatedDescuentoExterno = descuentosExternosRepository.save(descuentoExistente);
        return descuentosExternosMapper.toDTO(updatedDescuentoExterno);
    }

    @Override
    public void eliminarDescuentoExterno(Integer id) {
        DescuentosExternos descuentoExterno = descuentosExternosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Externo no encontrado"));
        descuentosExternosRepository.delete(descuentoExterno);
    }
}