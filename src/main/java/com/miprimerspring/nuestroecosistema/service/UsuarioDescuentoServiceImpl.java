package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.mapper.UsuarioDescuentoMapper;
import com.miprimerspring.nuestroecosistema.model.UsuarioDescuento;
import com.miprimerspring.nuestroecosistema.repository.UsuarioDescuentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioDescuentoServiceImpl implements UsuarioDescuentoService {

    private final UsuarioDescuentoRepository usuarioDescuentoRepository;
    private final UsuarioDescuentoMapper usuarioDescuentoMapper;

    @Autowired
    public UsuarioDescuentoServiceImpl(UsuarioDescuentoRepository usuarioDescuentoRepository, UsuarioDescuentoMapper usuarioDescuentoMapper) {
        this.usuarioDescuentoRepository = usuarioDescuentoRepository;
        this.usuarioDescuentoMapper = usuarioDescuentoMapper;
    }

    @Override
    public UsuarioDescuentoDTO crearUsuarioDescuento(UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuento usuarioDescuento = usuarioDescuentoMapper.toEntity(usuarioDescuentoDTO);
        usuarioDescuento = usuarioDescuentoRepository.save(usuarioDescuento);
        return usuarioDescuentoMapper.toDTO(usuarioDescuento);
    }

    @Override
    public UsuarioDescuentoDTO obtenerUsuarioDescuentoPorId(Long id) {
        return usuarioDescuentoRepository.findById(id)
                .map(usuarioDescuentoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<UsuarioDescuentoDTO> obtenerUsuarioDescuentosPorUsuarioId(Integer usuarioId) {
        List<UsuarioDescuento> usuarioDescuentos = usuarioDescuentoRepository.findByUsuarioId(usuarioId);
        return usuarioDescuentos.stream().map(usuarioDescuentoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDescuentoDTO> obtenerUsuarioDescuentosPorDescuentoId(Long descuentoId) {
        List<UsuarioDescuento> usuarioDescuentos = usuarioDescuentoRepository.findByDescuentoId(descuentoId);
        return usuarioDescuentos.stream().map(usuarioDescuentoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDescuentoDTO obtenerUsuarioDescuentoPorUsuarioYDescuento(Integer usuarioId, Long descuentoId) {
        UsuarioDescuento usuarioDescuento = usuarioDescuentoRepository.findByUsuarioAndDescuento(usuarioId, descuentoId);
        return usuarioDescuento != null ? usuarioDescuentoMapper.toDTO(usuarioDescuento) : null;
    }

    @Override
    public void eliminarUsuarioDescuento(Long id) {
        usuarioDescuentoRepository.deleteById(id);
    }
}