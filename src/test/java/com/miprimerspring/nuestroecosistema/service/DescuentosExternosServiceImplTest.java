package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoExternoDTO;
import com.miprimerspring.nuestroecosistema.model.DescuentosExternos;
import com.miprimerspring.nuestroecosistema.repository.DescuentosExternosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
public class DescuentosExternosServiceImplTest {

    @Mock
    private DescuentosExternosRepository descuentosExternosRepository;

    @InjectMocks
    private DescuentosExternosServiceImpl descuentosExternosServiceImpl;

    private DescuentoExternoDTO descuentoExternoDTO;
    private DescuentosExternos descuentoExterno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup de DTO
        descuentoExternoDTO = new DescuentoExternoDTO();
        descuentoExternoDTO.setEmpresa("Doggis");
        descuentoExternoDTO.setDescripcion("Descuento en Doggis");
        descuentoExternoDTO.setPorcentajeDescuento(10.0);
        descuentoExternoDTO.setCodigoDescuento("DOGG10");

        // Setup de entidad
        descuentoExterno = new DescuentosExternos();
        descuentoExterno.setEmpresa("Doggis");
        descuentoExterno.setDescripcion("Descuento en Doggis");
        descuentoExterno.setPorcentajeDescuento(10.0);
        descuentoExterno.setCodigoDescuento("DOGG10");
    }

    @Test
    void testObtenerDescuentosExternos() {
        when(descuentosExternosRepository.findAll()).thenReturn(Arrays.asList(descuentoExterno));

        // Llamada al servicio
        var resultado = descuentosExternosServiceImpl.obtenerDescuentosExternos();

        // Verificar el resultado
        assertThat(resultado).isNotNull();
        assertThat(resultado.size()).isEqualTo(1);
        assertThat(resultado.get(0).getEmpresa()).isEqualTo("Doggis");
    }

    @Test
    void testObtenerDescuentoExternoPorId_Existe() {
        when(descuentosExternosRepository.findById(1)).thenReturn(Optional.of(descuentoExterno));

        // Llamada al servicio
        var resultado = descuentosExternosServiceImpl.obtenerDescuentoExternoPorId(1L);

        // Verificar el resultado
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmpresa()).isEqualTo("Doggis");
    }

    @Test
    void testObtenerDescuentoExternoPorId_NoExiste() {
        when(descuentosExternosRepository.findById(1)).thenReturn(Optional.empty());

        // Llamada al servicio
        var resultado = descuentosExternosServiceImpl.obtenerDescuentoExternoPorId(1L);

        // Verificar que el resultado es null
        assertThat(resultado).isNull();
    }

    @Test
    void testCrearDescuentoExterno() {
        when(descuentosExternosRepository.save(any(DescuentosExternos.class))).thenReturn(descuentoExterno);

        // Llamada al servicio
        var resultado = descuentosExternosServiceImpl.crearDescuentoExterno(descuentoExternoDTO);

        // Verificar el resultado
        assertThat(resultado).isNotNull();
        assertThat(resultado.getEmpresa()).isEqualTo("Doggis");
        assertThat(resultado.getCodigoDescuento()).isEqualTo("DOGG10");
    }

    @Test
    void testActualizarDescuentoExterno() {
        when(descuentosExternosRepository.findById(1)).thenReturn(Optional.of(descuentoExterno));
        when(descuentosExternosRepository.save(any(DescuentosExternos.class))).thenReturn(descuentoExterno);

        // DTO de actualización
        descuentoExternoDTO.setDescripcion("Descuento en Doggis actualizado");

        // Llamada al servicio
        var resultado = descuentosExternosServiceImpl.actualizarDescuentoExterno(1L, descuentoExternoDTO);

        // Verificar el resultado
        assertThat(resultado).isNotNull();
        assertThat(resultado.getDescripcion()).isEqualTo("Descuento en Doggis actualizado");
    }

    @Test
    void testEliminarDescuentoExterno() {
        doNothing().when(descuentosExternosRepository).deleteById(1);

        // Llamada al servicio
        descuentosExternosServiceImpl.eliminarDescuentoExterno(1L);

        // Verificar que el método deleteById fue llamado correctamente
        verify(descuentosExternosRepository, Mockito.times(1)).deleteById(1);
    }

}

