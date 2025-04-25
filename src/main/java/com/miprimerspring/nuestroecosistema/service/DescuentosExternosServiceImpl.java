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

    @Autowired
    private DescuentosExternosRepository descuentosExternosRepository;

    @Override
    public List<DescuentoExternoDTO> obtenerDescuentosExternos() {
        List<DescuentosExternos> descuentos = descuentosExternosRepository.findAll();
        return descuentos.stream()
                .map(DescuentosExternosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoExternoDTO obtenerDescuentoExternoPorId(Long id) {
        Optional<DescuentosExternos> descuentoOpt = descuentosExternosRepository.findById(Math.toIntExact(id));
        return descuentoOpt.map(DescuentosExternosMapper::toDTO).orElse(null);
    }

    @Override
    public DescuentoExternoDTO crearDescuentoExterno(DescuentoExternoDTO descuentoExternoDTO) {
        DescuentosExternos descuento = DescuentosExternosMapper.toEntity(descuentoExternoDTO);
        DescuentosExternos savedDescuento = descuentosExternosRepository.save(descuento);
        return DescuentosExternosMapper.toDTO(savedDescuento);
    }

    @Override
    public DescuentoExternoDTO actualizarDescuentoExterno(Long id, DescuentoExternoDTO descuentoExternoDTO) {
        Optional<DescuentosExternos> descuentoOpt = descuentosExternosRepository.findById(Math.toIntExact(id));
        if (descuentoOpt.isPresent()) {
            DescuentosExternos descuento = descuentoOpt.get();
            descuento.setEmpresa(descuentoExternoDTO.getEmpresa());
            descuento.setDescripcion(descuentoExternoDTO.getDescripcion());
            descuento.setPorcentajeDescuento(descuentoExternoDTO.getPorcentajeDescuento());
            descuento.setCodigoDescuento(descuentoExternoDTO.getCodigoDescuento());
            DescuentosExternos updatedDescuento = descuentosExternosRepository.save(descuento);
            return DescuentosExternosMapper.toDTO(updatedDescuento);
        }
        return null;
    }

    @Override
    public void eliminarDescuentoExterno(Long id) {
        descuentosExternosRepository.deleteById(Math.toIntExact(id));
    }
}