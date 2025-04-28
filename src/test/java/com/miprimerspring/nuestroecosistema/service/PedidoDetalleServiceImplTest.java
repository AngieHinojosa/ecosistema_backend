package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PedidoDetalleDTO;
import com.miprimerspring.nuestroecosistema.mapper.PedidoDetalleMapper;
import com.miprimerspring.nuestroecosistema.model.PedidoDetalle;
import com.miprimerspring.nuestroecosistema.repository.PedidoDetalleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PedidoDetalleServiceImplTest {

    private PedidoDetalleServiceImpl pedidoDetalleService;
    private PedidoDetalleRepository pedidoDetalleRepository;
    private PedidoDetalleMapper pedidoDetalleMapper;

    @BeforeEach
    void setUp() {
        pedidoDetalleRepository = mock(PedidoDetalleRepository.class);
        pedidoDetalleMapper = mock(PedidoDetalleMapper.class);
        pedidoDetalleService = new PedidoDetalleServiceImpl(pedidoDetalleRepository, pedidoDetalleMapper);
    }

    // Test para crear un pedido detalle
    @Test
    void crearPedidoDetalle_ShouldReturnPedidoDetalleDTO() {
        PedidoDetalleDTO pedidoDetalleDTO = new PedidoDetalleDTO();
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        PedidoDetalle savedPedidoDetalle = new PedidoDetalle();

        when(pedidoDetalleMapper.toEntity(pedidoDetalleDTO)).thenReturn(pedidoDetalle);
        when(pedidoDetalleRepository.save(pedidoDetalle)).thenReturn(savedPedidoDetalle);
        when(pedidoDetalleMapper.toDTO(savedPedidoDetalle)).thenReturn(pedidoDetalleDTO);

        PedidoDetalleDTO result = pedidoDetalleService.crearPedidoDetalle(pedidoDetalleDTO);

        assertNotNull(result);
        verify(pedidoDetalleRepository, times(1)).save(pedidoDetalle);
    }

    // Test para obtener un pedido detalle por ID (exitoso)
    @Test
    void obtenerPedidoDetallePorId_ShouldReturnPedidoDetalleDTO() {
        Long id = 1L;
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
        PedidoDetalleDTO pedidoDetalleDTO = new PedidoDetalleDTO();

        when(pedidoDetalleRepository.findById(id)).thenReturn(Optional.of(pedidoDetalle));
        when(pedidoDetalleMapper.toDTO(pedidoDetalle)).thenReturn(pedidoDetalleDTO);

        PedidoDetalleDTO result = pedidoDetalleService.obtenerPedidoDetallePorId(id);

        assertNotNull(result);
        verify(pedidoDetalleRepository, times(1)).findById(id);
    }

    // Test para obtener un pedido detalle por ID (no encontrado)
    @Test
    void obtenerPedidoDetallePorId_ShouldThrowException() {
        Long id = 1L;

        when(pedidoDetalleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pedidoDetalleService.obtenerPedidoDetallePorId(id));
        verify(pedidoDetalleRepository, times(1)).findById(id);
    }
}
