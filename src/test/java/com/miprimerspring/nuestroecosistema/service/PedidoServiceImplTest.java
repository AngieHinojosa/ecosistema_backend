package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDTO;
import com.miprimerspring.nuestroecosistema.mapper.PedidoMapper;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceImplTest {

    private PedidoServiceImpl pedidoService;
    private PedidoRepository pedidoRepository;
    private PedidoMapper pedidoMapper;

    @BeforeEach
    void setUp() {
        pedidoRepository = mock(PedidoRepository.class);
        pedidoMapper = mock(PedidoMapper.class);
        pedidoService = new PedidoServiceImpl(pedidoRepository, pedidoMapper);
    }

    // Test para crear un pedido
    @Test
    void crearPedido_ShouldReturnPedidoDTO() {
        PedidoDTO pedidoDTO = new PedidoDTO();
        Pedido pedido = new Pedido();
        Pedido savedPedido = new Pedido();

        when(pedidoMapper.toEntity(pedidoDTO)).thenReturn(pedido);
        when(pedidoRepository.save(pedido)).thenReturn(savedPedido);
        when(pedidoMapper.toDTO(savedPedido)).thenReturn(pedidoDTO);

        PedidoDTO result = pedidoService.crearPedido(pedidoDTO);

        assertNotNull(result);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    // Test para obtener un pedido por ID (exitoso)
    @Test
    void obtenerPedidoPorId_ShouldReturnPedidoDTO() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        PedidoDTO pedidoDTO = new PedidoDTO();

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));
        when(pedidoMapper.toDTO(pedido)).thenReturn(pedidoDTO);

        PedidoDTO result = pedidoService.obtenerPedidoPorId(id);

        assertNotNull(result);
        verify(pedidoRepository, times(1)).findById(id);
    }

    // Test para obtener un pedido por ID (no encontrado)
    @Test
    void obtenerPedidoPorId_ShouldReturnNull() {
        Long id = 1L;

        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        PedidoDTO result = pedidoService.obtenerPedidoPorId(id);

        assertNull(result);
        verify(pedidoRepository, times(1)).findById(id);
    }
}
