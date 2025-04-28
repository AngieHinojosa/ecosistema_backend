package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CuentaBancariaDTO;
import com.miprimerspring.nuestroecosistema.exception.CuentaBancariaNotFoundException;
import com.miprimerspring.nuestroecosistema.mapper.CuentaBancariaMapper;
import com.miprimerspring.nuestroecosistema.model.CuentaBancaria;
import com.miprimerspring.nuestroecosistema.repository.CuentaBancariaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CuentaBancariaServiceImplTest {

    @InjectMocks
    private CuentaBancariaServiceImpl cuentaBancariaServiceImpl;

    @Mock
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Mock
    private CuentaBancariaMapper cuentaBancariaMapper;

    @BeforeEach
    void setUp() {
        cuentaBancariaServiceImpl = new CuentaBancariaServiceImpl(cuentaBancariaRepository, cuentaBancariaMapper);
    }

    @Test
    void testCrearCuentaBancaria() {
        CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
        cuentaBancariaDTO.setCuentaSaldo(BigDecimal.valueOf(5000.0));
        cuentaBancariaDTO.setUsuarioId(1L);
        cuentaBancariaDTO.setCuentaId(1L); // Agrega esto porque tu DTO necesita tener el ID también

        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setCuentaSaldo(BigDecimal.valueOf(5000.0));
        cuentaBancaria.setCuentaId(1L);

        when(cuentaBancariaMapper.toEntity(cuentaBancariaDTO)).thenReturn(cuentaBancaria);
        when(cuentaBancariaRepository.save(cuentaBancaria)).thenReturn(cuentaBancaria);
        when(cuentaBancariaMapper.toDTO(cuentaBancaria)).thenReturn(cuentaBancariaDTO);

        CuentaBancariaDTO resultado = cuentaBancariaServiceImpl.crearCuentaBancaria(cuentaBancariaDTO);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCuentaId()).isEqualTo(1L); // ✔ Correcto
        assertThat(resultado.getCuentaSaldo()).isEqualTo(BigDecimal.valueOf(5000.0)); // ✔ Correcto
    }

    @Test
    void testObtenerCuentaBancariaPorId() {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setCuentaId(1L);
        cuentaBancaria.setCuentaSaldo(BigDecimal.valueOf(3000.0));

        CuentaBancariaDTO cuentaBancariaDTO = new CuentaBancariaDTO();
        cuentaBancariaDTO.setCuentaId(1L);
        cuentaBancariaDTO.setCuentaSaldo(BigDecimal.valueOf(3000.0));
        cuentaBancariaDTO.setUsuarioId(1L);

        when(cuentaBancariaRepository.findById(1L)).thenReturn(Optional.of(cuentaBancaria));
        when(cuentaBancariaMapper.toDTO(cuentaBancaria)).thenReturn(cuentaBancariaDTO);

        CuentaBancariaDTO resultado = cuentaBancariaServiceImpl.obtenerCuentaBancariaPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCuentaSaldo()).isEqualTo(BigDecimal.valueOf(3000.0)); // ✔
    }

    @Test
    void testObtenerCuentasPorUsuario() {
        CuentaBancaria cuenta1 = new CuentaBancaria();
        cuenta1.setCuentaSaldo(BigDecimal.valueOf(1000.0));
        cuenta1.setCuentaId(1L);

        CuentaBancaria cuenta2 = new CuentaBancaria();
        cuenta2.setCuentaSaldo(BigDecimal.valueOf(2000.0));
        cuenta2.setCuentaId(1L);

        when(cuentaBancariaRepository.findByUsuarioId(1L)).thenReturn(Arrays.asList(cuenta1, cuenta2));

        CuentaBancariaDTO dto1 = new CuentaBancariaDTO();
        dto1.setCuentaSaldo(BigDecimal.valueOf(1000.0));
        dto1.setUsuarioId(1L);

        CuentaBancariaDTO dto2 = new CuentaBancariaDTO();
        dto2.setCuentaSaldo(BigDecimal.valueOf(2000.0));
        dto2.setUsuarioId(1L);

        when(cuentaBancariaMapper.toDTO(cuenta1)).thenReturn(dto1);
        when(cuentaBancariaMapper.toDTO(cuenta2)).thenReturn(dto2);

        List<CuentaBancariaDTO> resultado = cuentaBancariaServiceImpl.obtenerCuentasPorUsuario(1L);

        assertThat(resultado)
                .extracting(CuentaBancariaDTO::getCuentaSaldo)
                .usingComparatorForType(BigDecimal::compareTo, BigDecimal.class)
                .containsExactlyInAnyOrder(
                        BigDecimal.valueOf(1000.0),
                        BigDecimal.valueOf(2000.0)
                );
    }

    @Test
    void testActualizarCuentaBancaria() {
        CuentaBancaria cuentaExistente = new CuentaBancaria();
        cuentaExistente.setCuentaId(1L);
        cuentaExistente.setCuentaSaldo(BigDecimal.valueOf(1500.0));

        CuentaBancariaDTO nuevaCuentaBancariaDTO = new CuentaBancariaDTO();
        nuevaCuentaBancariaDTO.setCuentaSaldo(BigDecimal.valueOf(2500.0));

        CuentaBancaria cuentaActualizada = new CuentaBancaria();
        cuentaActualizada.setCuentaId(1L);
        cuentaActualizada.setCuentaSaldo(BigDecimal.valueOf(2500.0));

        when(cuentaBancariaRepository.findById(1L)).thenReturn(Optional.of(cuentaExistente));
        when(cuentaBancariaMapper.toEntity(nuevaCuentaBancariaDTO)).thenReturn(cuentaActualizada);
        when(cuentaBancariaRepository.save(cuentaActualizada)).thenReturn(cuentaActualizada);
        when(cuentaBancariaMapper.toDTO(cuentaActualizada)).thenReturn(nuevaCuentaBancariaDTO);

        CuentaBancariaDTO resultado = cuentaBancariaServiceImpl.actualizarCuentaBancaria(1L, nuevaCuentaBancariaDTO);

        assertThat(resultado.getCuentaSaldo()).isEqualByComparingTo(BigDecimal.valueOf(2500.0));
    }

    @Test
    void testEliminarCuentaBancaria() {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();
        cuentaBancaria.setCuentaId(1L);

        when(cuentaBancariaRepository.findById(1L)).thenReturn(Optional.of(cuentaBancaria));

        cuentaBancariaServiceImpl.eliminarCuentaBancaria(1L);

        verify(cuentaBancariaRepository, times(1)).delete(cuentaBancaria);
    }
}
