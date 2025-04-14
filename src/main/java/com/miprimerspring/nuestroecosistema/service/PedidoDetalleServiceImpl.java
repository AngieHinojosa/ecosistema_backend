package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.mapper.PedidoDetalleMapper;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import com.miprimerspring.nuestroecosistema.repository.PedidoDetalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final PedidoDetalleMapper pedidoDetalleMapper;

    @Autowired
    public PedidoDetalleServiceImpl(PedidoDetalleRepository pedidoDetalleRepository, PedidoDetalleMapper pedidoDetalleMapper) {
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.pedidoDetalleMapper = pedidoDetalleMapper;
    }

    @Override
    public PedidoDetalleDTO crearPedidoDetalle(PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalle pedidoDetalle = pedidoDetalleMapper.toEntity(pedidoDetalleDTO);
        PedidoDetalle savedPedidoDetalle = pedidoDetalleRepository.save(pedidoDetalle);
        return pedidoDetalleMapper.toDTO(savedPedidoDetalle);
    }

    @Override
    public PedidoDetalleDTO obtenerPedidoDetallePorId(Long id) {
        PedidoDetalle pedidoDetalle = pedidoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
        return pedidoDetalleMapper.toDTO(pedidoDetalle);
    }

    @Override
    public List<PedidoDetalleDTO> obtenerDetallesPorPedido(Integer pedidoId) {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findByPedido_PedidoId(pedidoId);
        return detalles.stream()
                .map(pedidoDetalleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoDetalleDTO> obtenerDetallesPorProducto(Integer productoId) {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findByProducto_ProductoId(productoId);
        return detalles.stream()
                .map(pedidoDetalleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoDetalleDTO> obtenerTodosDetalles() {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findAll();
        return detalles.stream()
                .map(pedidoDetalleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDetalleDTO actualizarPedidoDetalle(Long id, PedidoDetalleDTO pedidoDetalleDTO) {
        PedidoDetalle pedidoDetalleExistente = pedidoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
        pedidoDetalleExistente = pedidoDetalleMapper.toEntity(pedidoDetalleDTO);
        pedidoDetalleExistente.setDetalleId(id);  // Mantener el ID
        PedidoDetalle updatedPedidoDetalle = pedidoDetalleRepository.save(pedidoDetalleExistente);
        return pedidoDetalleMapper.toDTO(updatedPedidoDetalle);
    }

    @Override
    public void eliminarPedidoDetalle(Long id) {
        PedidoDetalle pedidoDetalle = pedidoDetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de pedido no encontrado"));
        pedidoDetalleRepository.delete(pedidoDetalle);
    }
}