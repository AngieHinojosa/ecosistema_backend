package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.mapper.DescuentoAplicadoMapper;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.repository.DescuentoAplicadoRepository;
import com.miprimerspring.nuestroecosistema.repository.DescuentosExternosRepository;
import com.miprimerspring.nuestroecosistema.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DescuentoAplicadoServiceImpl implements DescuentoAplicadoService {

    private final DescuentoAplicadoRepository descuentoAplicadoRepository;
    private final DescuentoAplicadoMapper descuentoAplicadoMapper;

    @Autowired
    public DescuentoAplicadoServiceImpl(DescuentoAplicadoRepository descuentoAplicadoRepository, DescuentoAplicadoMapper descuentoAplicadoMapper) {
        this.descuentoAplicadoRepository = descuentoAplicadoRepository;
        this.descuentoAplicadoMapper = descuentoAplicadoMapper;
    }

    @Override
    public DescuentoAplicadoDTO crearDescuentoAplicado(DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicado descuentoAplicado = descuentoAplicadoMapper.toEntity(descuentoAplicadoDTO);
        DescuentoAplicado savedDescuentoAplicado = descuentoAplicadoRepository.save(descuentoAplicado);
        return descuentoAplicadoMapper.toDTO(savedDescuentoAplicado);
    }

    @Override
    public DescuentoAplicadoDTO obtenerDescuentoAplicadoPorId(Long id) {
        DescuentoAplicado descuentoAplicado = descuentoAplicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Aplicado no encontrado"));
        return descuentoAplicadoMapper.toDTO(descuentoAplicado);
    }

    @Override
    public List<DescuentoAplicadoDTO> obtenerDescuentosPorPedido(Integer pedidoId) {
        List<DescuentoAplicado> descuentos = descuentoAplicadoRepository.findByPedido_PedidoId(pedidoId);
        return descuentos.stream()
                .map(descuentoAplicadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DescuentoAplicadoDTO> obtenerDescuentosPorDescuento(Integer descuentoId) {
        List<DescuentoAplicado> descuentos = descuentoAplicadoRepository.findByDescuentosExternos_DescuentoId(descuentoId);
        return descuentos.stream()
                .map(descuentoAplicadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DescuentoAplicadoDTO> obtenerTodosDescuentosAplicados() {
        List<DescuentoAplicado> descuentos = descuentoAplicadoRepository.findAll();
        return descuentos.stream()
                .map(descuentoAplicadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoAplicadoDTO actualizarDescuentoAplicado(Long id, DescuentoAplicadoDTO descuentoAplicadoDTO) {
        DescuentoAplicado descuentoExistente = descuentoAplicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Aplicado no encontrado"));
        descuentoExistente = descuentoAplicadoMapper.toEntity(descuentoAplicadoDTO);
        descuentoExistente.setDescuentoAplicadoId(id);  // Mantener el ID
        DescuentoAplicado updatedDescuentoAplicado = descuentoAplicadoRepository.save(descuentoExistente);
        return descuentoAplicadoMapper.toDTO(updatedDescuentoAplicado);
    }

    @Override
    public void eliminarDescuentoAplicado(Long id) {
        DescuentoAplicado descuentoAplicado = descuentoAplicadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Descuento Aplicado no encontrado"));
        descuentoAplicadoRepository.delete(descuentoAplicado);
    }
}