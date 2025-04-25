package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.dto.UsuarioDescuentoDTO;
import com.miprimerspring.nuestroecosistema.mapper.DescuentoAplicadoMapper;
import com.miprimerspring.nuestroecosistema.mapper.UsuarioDescuentoMapper;
import com.miprimerspring.nuestroecosistema.model.*;
import com.miprimerspring.nuestroecosistema.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DescuentoAplicadoServiceImpl implements DescuentoAplicadoService {

    @Autowired
    private DescuentoAplicadoRepository descuentoAplicadoRepository;

    @Override
    public List<DescuentoAplicadoDTO> obtenerDescuentosAplicados() {
        List<DescuentoAplicado> descuentos = descuentoAplicadoRepository.findAll();
        return descuentos.stream()
                .map(DescuentoAplicadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id) {
        Optional<DescuentoAplicado> descuentoOpt = descuentoAplicadoRepository.findById(id);
        return descuentoOpt.map(DescuentoAplicadoMapper::toDTO).orElse(null);
    }

    @Override
    public DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicado descuento = DescuentoAplicadoMapper.toEntity(descuentoAplicadoDTO);
        DescuentoAplicado savedDescuento = descuentoAplicadoRepository.save(descuento);
        return DescuentoAplicadoMapper.toDTO(savedDescuento);
    }

    @Override
    public DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO) {
        Optional<DescuentoAplicado> descuentoOpt = descuentoAplicadoRepository.findById(id);
        if (descuentoOpt.isPresent()) {
            DescuentoAplicado descuento = descuentoOpt.get();
            descuento.setDescripcion(descuentoAplicadoDTO.getDescripcion());
            descuento.setPorcentajeDescuento(descuentoAplicadoDTO.getPorcentajeDescuento());
            descuento.setDescuentoAplicadoTotal(descuentoAplicadoDTO.getDescuentoAplicadoTotal());
            descuento.setDescuentoCodigo(descuentoAplicadoDTO.getDescuentoCodigo());
            DescuentoAplicado updatedDescuento = descuentoAplicadoRepository.save(descuento);
            return DescuentoAplicadoMapper.toDTO(updatedDescuento);
        }
        return null;
    }

    @Override
    public void eliminarDescuentoAplicado(Long id) {
        descuentoAplicadoRepository.deleteById(id);
    }
}