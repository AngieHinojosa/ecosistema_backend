package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.TransaccionDTO;
import com.miprimerspring.nuestroecosistema.mapper.TransaccionMapper;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.model.Transaccion;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import com.miprimerspring.nuestroecosistema.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransaccionServiceImplTest {

    private TransaccionServiceImpl transaccionService;
    private TransaccionRepository transaccionRepository;
    private TransaccionMapper transaccionMapper;
    private CuentaBancariaRepository cuentaBancariaRepository;

    @BeforeEach
    void setUp() {
        transaccionRepository = mock(TransaccionRepository.class);
        transaccionMapper = mock(TransaccionMapper.class);
        cuentaBancariaRepository = mock(CuentaBancariaRepository.class);
        transaccionService = new TransaccionServiceImpl(transaccionRepository, transaccionMapper, cuentaBancariaRepository);
    }

    // Test para crear una transacción
    @Test
    void crearTransaccion_ShouldReturnTransaccionDTO() throws Exception {
        // Preparación
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setCuentaId(1L);
        transaccionDTO.setTransaccionOrigenId(2L);
        transaccionDTO.setTransaccionDestinoId(3L);
        transaccionDTO.setTransaccionMonto(100.0);

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setCuentaSaldo(BigDecimal.valueOf(200));

        CuentaBancaria cuentaOrigen = new CuentaBancaria();
        cuentaOrigen.setCuentaSaldo(BigDecimal.valueOf(150));

        CuentaBancaria cuentaDestino = new CuentaBancaria();
        cuentaDestino.setCuentaSaldo(BigDecimal.valueOf(50));

        // Crear la transacción y establecer el id
        Transaccion transaccion = new Transaccion();
        transaccion.setTransaccionId(1L);  // Usar el setter si existe para establecer el id

        // Mocking de las dependencias
        when(cuentaBancariaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(cuentaBancariaRepository.findById(2L)).thenReturn(Optional.of(cuentaOrigen));
        when(cuentaBancariaRepository.findById(3L)).thenReturn(Optional.of(cuentaDestino));
        when(transaccionMapper.toEntity(transaccionDTO)).thenReturn(transaccion);
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);
        when(transaccionMapper.toDTO(transaccion)).thenReturn(transaccionDTO);

        // Ejecución
        TransaccionDTO result = transaccionService.crearTransaccion(transaccionDTO);

        // Verificación
        assertNotNull(result);
        verify(cuentaBancariaRepository, times(1)).save(cuentaOrigen);
        verify(cuentaBancariaRepository, times(1)).save(cuentaDestino);
    }

    @Test
    void obtenerTransaccionPorId_ShouldReturnTransaccionDTO() {
        Long id = 1L;
        Transaccion transaccion = new Transaccion();
        transaccion.setTransaccionId(id);  // Establecer el id usando el setter o el constructor

        TransaccionDTO transaccionDTO = new TransaccionDTO();

        // Mocking de las dependencias
        when(transaccionRepository.findById(id)).thenReturn(Optional.of(transaccion));
        when(transaccionMapper.toDTO(transaccion)).thenReturn(transaccionDTO);

        // Ejecución
        TransaccionDTO result = transaccionService.obtenerTransaccionPorId(id);

        // Verificación
        assertNotNull(result);
        verify(transaccionRepository, times(1)).findById(id);
    }
}