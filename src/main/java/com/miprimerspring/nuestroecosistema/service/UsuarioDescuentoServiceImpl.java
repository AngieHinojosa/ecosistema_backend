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

    @Autowired
    private UsuarioDescuentoRepository usuarioDescuentoRepository;

    @Override
    public List<UsuarioDescuentoDTO> obtenerUsuarioDescuentos() {
        List<UsuarioDescuento> descuentos = usuarioDescuentoRepository.findAll();
        return descuentos.stream()
                .map(UsuarioDescuentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDescuentoDTO obtenerUsuarioDescuentoPorId(Long id) {
        Optional<UsuarioDescuento> descuentoOpt = usuarioDescuentoRepository.findById(id);
        return descuentoOpt.map(UsuarioDescuentoMapper::toDTO).orElse(null);
    }

    @Override
    public UsuarioDescuentoDTO crearUsuarioDescuento(UsuarioDescuentoDTO usuarioDescuentoDTO) {
        UsuarioDescuento usuarioDescuento = UsuarioDescuentoMapper.toEntity(usuarioDescuentoDTO);
        UsuarioDescuento savedUsuarioDescuento = usuarioDescuentoRepository.save(usuarioDescuento);
        return UsuarioDescuentoMapper.toDTO(savedUsuarioDescuento);
    }

    @Override
    public void eliminarUsuarioDescuento(Long id) {
        usuarioDescuentoRepository.deleteById(id);
    }
}