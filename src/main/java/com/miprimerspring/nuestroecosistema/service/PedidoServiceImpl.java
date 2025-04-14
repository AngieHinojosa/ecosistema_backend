package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;
import com.miprimerspring.nuestroecosistema.mapper.PedidoMapper;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedido);
    }

    @Override
    public PedidoDTO obtenerPedidoPorId(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(pedidoMapper::toDTO).orElse(null); // Devuelve un solo DTO o null si no lo encuentra
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorUsuario(Integer usuarioId) {
        List<Pedido> pedidos = pedidoRepository.findByUsuario_UsuarioId(usuarioId);
        return pedidoMapper.toDTO(pedidos); // Mapea la lista de pedidos a DTOs
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorDireccion(Integer direccionId) {
        List<Pedido> pedidos = pedidoRepository.findByDireccion_DireccionId(direccionId);
        return pedidoMapper.toDTO(pedidos); // Mapea la lista de pedidos a DTOs
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorEstado(String estado) {
        List<Pedido> pedidos = pedidoRepository.buscarPorEstado(estado);
        return pedidoMapper.toDTO(pedidos); // Mapea la lista de pedidos a DTOs
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorFecha(Date fecha) {
        List<Pedido> pedidos = pedidoRepository.findByPedidoFecha(fecha);
        return pedidoMapper.toDTO(pedidos); // Mapea la lista de pedidos a DTOs
    }

    @Override
    public List<PedidoDTO> obtenerTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toDTO(pedidos); // Mapea la lista de pedidos a DTOs
    }

    @Override
    public PedidoDTO actualizarPedido(Long id, PedidoDTO pedidoDTO) {
        if (pedidoRepository.existsById(id)) {
            Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
            pedido.setPedidoId(id);
            pedido = pedidoRepository.save(pedido);
            return pedidoMapper.toDTO(pedido); // Mapea el pedido actualizado a DTO
        }
        return null; // Retorna null si no existe el pedido
    }

    @Override
    public void eliminarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id); // Elimina el pedido por id
        }
    }
}