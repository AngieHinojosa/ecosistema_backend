package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PagoDTO;
import com.miprimerspring.nuestroecosistema.exception.PagoNotFoundException;
import com.miprimerspring.nuestroecosistema.mapper.PagoMapper;
import com.miprimerspring.nuestroecosistema.model.Pago;
import com.miprimerspring.nuestroecosistema.repository.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PagosServiceImplTest {

    private PagosServiceImpl pagosService;
    private PagoRepository pagoRepository;
    private PagoMapper pagoMapper;

    @BeforeEach
    void setUp() {
        pagoRepository = mock(PagoRepository.class);
        pagoMapper = mock(PagoMapper.class);
        pagosService = new PagosServiceImpl(pagoRepository, pagoMapper);
    }

    // Test para crear un pago
    @Test
    void crearPago_ShouldReturnPagoDTO() {
        PagoDTO pagoDTO = new PagoDTO();
        Pago pago = new Pago();
        Pago savedPago = new Pago();

        when(pagoMapper.toEntity(pagoDTO)).thenReturn(pago);
        when(pagoRepository.save(pago)).thenReturn(savedPago);
        when(pagoMapper.toDTO(savedPago)).thenReturn(pagoDTO);

        PagoDTO result = pagosService.crearPago(pagoDTO);

        assertNotNull(result);
        verify(pagoRepository, times(1)).save(pago);
    }

    // Test para obtener un pago por ID (exitoso)
    @Test
    void obtenerPagoPorId_ShouldReturnPagoDTO() {
        Long id = 1L;
        Pago pago = new Pago();
        PagoDTO pagoDTO = new PagoDTO();

        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(pagoDTO);

        PagoDTO result = pagosService.obtenerPagoPorId(id);

        assertNotNull(result);
        verify(pagoRepository, times(1)).findById(id);
    }

    // Test para obtener un pago por ID (no encontrado)
    @Test
    void obtenerPagoPorId_ShouldThrowPagoNotFoundException() {
        Long id = 1L;

        when(pagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PagoNotFoundException.class, () -> pagosService.obtenerPagoPorId(id));
        verify(pagoRepository, times(1)).findById(id);
    }

    // Test para eliminar un pago (exitoso)
    @Test
    void eliminarPago_ShouldRemovePago() {
        Long id = 1L;
        Pago pago = new Pago();

        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));

        pagosService.eliminarPago(id);

        verify(pagoRepository, times(1)).delete(pago);
    }

    // Test para eliminar un pago (no encontrado)
    @Test
    void eliminarPago_ShouldThrowPagoNotFoundException() {
        Long id = 1L;

        when(pagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PagoNotFoundException.class, () -> pagosService.eliminarPago(id));
        verify(pagoRepository, times(1)).findById(id);
    }
}
