package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.LogsActividadDTO;
import com.miprimerspring.nuestroecosistema.mapper.LogsActividadMapper;
import com.miprimerspring.nuestroecosistema.model.LogsActividad;
import com.miprimerspring.nuestroecosistema.repository.LogsActividadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogsActividadServiceImplTest {

    @InjectMocks
    private LogsActividadServiceImpl logsActividadService;

    @Mock
    private LogsActividadRepository logsActividadRepository;

    @Mock
    private LogsActividadMapper logsActividadMapper;

    private LogsActividadDTO logsActividadDTO;
    private LogsActividad logsActividad;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Datos de prueba
        logsActividadDTO = new LogsActividadDTO();
        logsActividadDTO.setLogAccion("Acción de prueba");

        logsActividad = new LogsActividad();
        logsActividad.setLogAccion("Acción de prueba");
    }

    @Test
    void crearLogActividad() {
        // Simula el comportamiento del repositorio
        when(logsActividadMapper.toEntity(logsActividadDTO)).thenReturn(logsActividad);
        when(logsActividadRepository.save(logsActividad)).thenReturn(logsActividad);
        when(logsActividadMapper.toDTO(logsActividad)).thenReturn(logsActividadDTO);

        // Ejecuta el método
        LogsActividadDTO result = logsActividadService.crearLogActividad(logsActividadDTO);

        // Verifica los resultados
        assertNotNull(result);
        assertEquals("Acción de prueba", result.getLogAccion());
        verify(logsActividadRepository, times(1)).save(logsActividad);
    }

    @Test
    void obtenerLogActividadPorId() {
        // Simula el comportamiento del repositorio
        when(logsActividadRepository.findById(1L)).thenReturn(java.util.Optional.of(logsActividad));
        when(logsActividadMapper.toDTO(logsActividad)).thenReturn(logsActividadDTO);

        // Ejecuta el método
        LogsActividadDTO result = logsActividadService.obtenerLogActividadPorId(1L);

        // Verifica los resultados
        assertNotNull(result);
        assertEquals("Acción de prueba", result.getLogAccion());
        verify(logsActividadRepository, times(1)).findById(1L);
    }
}