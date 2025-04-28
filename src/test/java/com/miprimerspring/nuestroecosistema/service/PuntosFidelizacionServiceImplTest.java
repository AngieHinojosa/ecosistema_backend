package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.PuntosFidelizacionDTO;
import com.miprimerspring.nuestroecosistema.mapper.PuntosFidelizacionMapper;
import com.miprimerspring.nuestroecosistema.model.PuntosFidelizacion;
import com.miprimerspring.nuestroecosistema.repository.PuntosFidelizacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PuntosFidelizacionServiceImplTest {

    private PuntosFidelizacionServiceImpl puntosFidelizacionService;
    private PuntosFidelizacionRepository puntosFidelizacionRepository;
    private PuntosFidelizacionMapper puntosFidelizacionMapper;

    @BeforeEach
    void setUp() {
        puntosFidelizacionRepository = mock(PuntosFidelizacionRepository.class);
        puntosFidelizacionMapper = mock(PuntosFidelizacionMapper.class);
        puntosFidelizacionService = new PuntosFidelizacionServiceImpl(puntosFidelizacionRepository, puntosFidelizacionMapper);
    }

    // Test para crear un punto de fidelización
    @Test
    void crearPuntoFidelizacion_ShouldReturnPuntoFidelizacionDTO() {
        PuntosFidelizacionDTO puntosFidelizacionDTO = new PuntosFidelizacionDTO();
        PuntosFidelizacion puntosFidelizacion = new PuntosFidelizacion();
        PuntosFidelizacion savedPuntosFidelizacion = new PuntosFidelizacion();

        when(puntosFidelizacionMapper.toEntity(puntosFidelizacionDTO)).thenReturn(puntosFidelizacion);
        when(puntosFidelizacionRepository.save(puntosFidelizacion)).thenReturn(savedPuntosFidelizacion);
        when(puntosFidelizacionMapper.toDTO(savedPuntosFidelizacion)).thenReturn(puntosFidelizacionDTO);

        PuntosFidelizacionDTO result = puntosFidelizacionService.crearPuntoFidelizacion(puntosFidelizacionDTO);

        assertNotNull(result);
        verify(puntosFidelizacionRepository, times(1)).save(puntosFidelizacion);
    }

    // Test para obtener un punto de fidelización por ID
    @Test
    void obtenerPuntoFidelizacionPorId_ShouldReturnPuntoFidelizacionDTO() {
        Long id = 1L;
        PuntosFidelizacion puntosFidelizacion = new PuntosFidelizacion();
        PuntosFidelizacionDTO puntosFidelizacionDTO = new PuntosFidelizacionDTO();

        when(puntosFidelizacionRepository.findById(id)).thenReturn(Optional.of(puntosFidelizacion));
        when(puntosFidelizacionMapper.toDTO(puntosFidelizacion)).thenReturn(puntosFidelizacionDTO);

        PuntosFidelizacionDTO result = puntosFidelizacionService.obtenerPuntoFidelizacionPorId(id);

        assertNotNull(result);
        verify(puntosFidelizacionRepository, times(1)).findById(id);
    }
}
