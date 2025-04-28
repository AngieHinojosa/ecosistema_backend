package com.miprimerspring.nuestroecosistema.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.miprimerspring.nuestroecosistema.dto.DireccionDTO;
import com.miprimerspring.nuestroecosistema.mapper.DireccionMapper;
import com.miprimerspring.nuestroecosistema.model.Direccion;
import com.miprimerspring.nuestroecosistema.repository.DireccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DireccionServiceImplTest {

    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private DireccionMapper direccionMapper;

    @InjectMocks
    private DireccionServiceImpl direccionService;

    private DireccionDTO direccionDTO;
    private Direccion direccion;

    @BeforeEach
    void setUp() {
        // Crear objetos para los tests
        direccionDTO = new DireccionDTO();
        direccionDTO.setDireccionCalle("Calle 1");
        direccionDTO.setDireccionCiudad("Ciudad 1");

        direccion = new Direccion();
        direccion.setDireccionCalle("Calle 1");
        direccion.setDireccionCiudad("Ciudad 1");
    }

    @Test
    void testCrearDireccion() {
        // Configurar los mocks
        when(direccionMapper.toEntity(direccionDTO)).thenReturn(direccion);  // Mapeo de DTO a entidad
        when(direccionRepository.save(direccion)).thenReturn(direccion);     // Guardado de entidad
        when(direccionMapper.toDTO(direccion)).thenReturn(direccionDTO);     // Mapeo de entidad a DTO

        // Ejecutar el método
        DireccionDTO result = direccionService.crearDireccion(direccionDTO);

        // Depuración: Verifica que el resultado no sea nulo
        System.out.println("Resultado de la creación: " + result);  // Añadir más detalles

        // Verificar el resultado
        assertNotNull(result, "El resultado no debe ser nulo");

        // Verificar que los datos del DTO son correctos
        assertEquals(direccionDTO.getDireccionCalle(), result.getDireccionCalle(), "La calle debe coincidir");
        assertEquals(direccionDTO.getDireccionCiudad(), result.getDireccionCiudad(), "La ciudad debe coincidir");

        // Verificar que el método save de direccionRepository fue llamado
        verify(direccionRepository).save(direccion);
    }

    @Test
    void testObtenerDireccionPorId() {
        // Configurar el mock
        when(direccionRepository.findById(1)).thenReturn(Optional.of(direccion));
        when(direccionMapper.toDTO(direccion)).thenReturn(direccionDTO);

        // Ejecutar el método
        DireccionDTO result = direccionService.obtenerDireccionPorId(1);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(direccionDTO.getDireccionCalle(), result.getDireccionCalle());
        verify(direccionRepository).findById(1);
    }

    @Test
    void testObtenerDireccionesPorUsuarioId() {
        // Configurar el mock
        List<Direccion> direcciones = List.of(direccion);
        when(direccionRepository.findByUsuario_UsuarioId(1)).thenReturn(direcciones);
        when(direccionMapper.toDTO(direccion)).thenReturn(direccionDTO);

        // Ejecutar el método
        List<DireccionDTO> result = direccionService.obtenerDireccionesPorUsuarioId(1);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(direccionDTO.getDireccionCalle(), result.get(0).getDireccionCalle());
        verify(direccionRepository).findByUsuario_UsuarioId(1);
    }

    @Test
    void testObtenerTodasDirecciones() {
        // Configurar el mock
        List<Direccion> direcciones = List.of(direccion);
        when(direccionRepository.findAll()).thenReturn(direcciones);
        when(direccionMapper.toDTO(direccion)).thenReturn(direccionDTO);

        // Ejecutar el método
        List<DireccionDTO> result = direccionService.obtenerTodasDirecciones();

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(direccionDTO.getDireccionCalle(), result.get(0).getDireccionCalle());
        verify(direccionRepository).findAll();
    }

    @Test
    void testActualizarDireccion() {
        // Configurar los mocks
        when(direccionRepository.findById(1)).thenReturn(Optional.of(direccion));
        when(direccionMapper.toEntity(direccionDTO)).thenReturn(direccion);
        when(direccionRepository.save(direccion)).thenReturn(direccion);
        when(direccionMapper.toDTO(direccion)).thenReturn(direccionDTO);

        // Ejecutar el método
        DireccionDTO result = direccionService.actualizarDireccion(1, direccionDTO);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(direccionDTO.getDireccionCalle(), result.getDireccionCalle());
        verify(direccionRepository).findById(1);
        verify(direccionRepository).save(direccion);
    }

    @Test
    void testEliminarDireccion() {
        // Configurar el mock
        when(direccionRepository.findById(1)).thenReturn(Optional.of(direccion));

        // Ejecutar el método
        direccionService.eliminarDireccion(1);

        // Verificar que se haya llamado al método delete
        verify(direccionRepository).delete(direccion);
    }

    @Test
    void testCrearDireccion_Exception() {
        // Probar la excepción si no se encuentra la dirección
        when(direccionRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            direccionService.obtenerDireccionPorId(2);
        });
    }
}

