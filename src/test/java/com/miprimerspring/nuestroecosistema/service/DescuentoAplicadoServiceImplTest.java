package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.DescuentoAplicadoDTO;
import com.miprimerspring.nuestroecosistema.mapper.DescuentoAplicadoMapper;
import com.miprimerspring.nuestroecosistema.model.DescuentoAplicado;
import com.miprimerspring.nuestroecosistema.model.Pedido;
import com.miprimerspring.nuestroecosistema.repository.DescuentoAplicadoRepository;
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
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class DescuentoAplicadoServiceImplTest {

    @Mock
    private DescuentoAplicadoRepository descuentoAplicadoRepository;

    @InjectMocks
    private DescuentoAplicadoServiceImpl descuentoAplicadoServiceImpl;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testObtenerDescuentosAplicados() {
        // Crear los objetos DescuentoAplicado con Double en lugar de BigDecimal
        DescuentoAplicado descuento1 = new DescuentoAplicado();
        descuento1.setDescuentoAplicadoId(1L);
        descuento1.setDescripcion("10% off");
        descuento1.setPorcentajeDescuento(10.0);  // Usando Double en lugar de BigDecimal
        descuento1.setDescuentoAplicadoTotal(0.0);  // Usando Double en lugar de BigDecimal
        descuento1.setDescuentoCodigo("CODE10");

        // Crear un Pedido para asociarlo al descuento
        Pedido pedido1 = new Pedido();
        pedido1.setPedidoId(1L);  // Establecemos un ID para el pedido
        descuento1.setPedido(pedido1);  // Asociamos el pedido al descuento

        DescuentoAplicado descuento2 = new DescuentoAplicado();
        descuento2.setDescuentoAplicadoId(2L);
        descuento2.setDescripcion("20% off");
        descuento2.setPorcentajeDescuento(20.0);  // Usando Double en lugar de BigDecimal
        descuento2.setDescuentoAplicadoTotal(0.0);  // Usando Double en lugar de BigDecimal
        descuento2.setDescuentoCodigo("CODE20");

        // Crear un Pedido para el segundo descuento
        Pedido pedido2 = new Pedido();
        pedido2.setPedidoId(2L);  // Establecemos un ID para el pedido
        descuento2.setPedido(pedido2);  // Asociamos el pedido al descuento

        // Definir el comportamiento del mock para el repositorio
        when(descuentoAplicadoRepository.findAll()).thenReturn(Arrays.asList(descuento1, descuento2));

        // Llamar al servicio y obtener los resultados
        List<DescuentoAplicadoDTO> resultado = descuentoAplicadoServiceImpl.obtenerDescuentosAplicados();

        // Verificar los resultados con assertions
        assertThat(resultado).hasSize(2);
        assertThat(resultado).extracting(DescuentoAplicadoDTO::getDescripcion)
                .containsExactlyInAnyOrder("10% off", "20% off");
    }

    @Test
    void testObtenerDescuentoAplicadoPorId_Existe() {
        // Crear el objeto DescuentoAplicado con el constructor por defecto y luego settear los valores
        DescuentoAplicado descuento = new DescuentoAplicado();
        descuento.setDescuentoAplicadoId(1L);
        descuento.setDescripcion("15% off");
        descuento.setPorcentajeDescuento(15.0);  // Usando Double
        descuento.setDescuentoAplicadoTotal(0.0);  // Usando Double
        descuento.setDescuentoCodigo("CODE15");

        // Crear un Pedido y asociarlo al DescuentoAplicado
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);  // Establecer un ID de pedido
        descuento.setPedido(pedido);  // Asociar el pedido al descuento

        // Definir el comportamiento del mock para el repositorio
        when(descuentoAplicadoRepository.findById(1L)).thenReturn(Optional.of(descuento));

        // Llamar al servicio y obtener el resultado
        DescuentoAplicadoDTO resultado = descuentoAplicadoServiceImpl.obtenerDescuentoAplicadoPorId(1L);

        // Verificar los resultados con assertions
        assertThat(resultado).isNotNull();
        assertThat(resultado.getDescripcion()).isEqualTo("15% off");
    }

    @Test
    void testObtenerDescuentoAplicadoPorId_NoExiste() {
        when(descuentoAplicadoRepository.findById(1L)).thenReturn(Optional.empty());

        DescuentoAplicadoDTO resultado = descuentoAplicadoServiceImpl.obtenerDescuentoAplicadoPorId(1L);

        assertThat(resultado).isNull();
    }

    @Test
    void testCrearDescuentoAplicado() {
        // Crear el DTO de entrada
        DescuentoAplicadoDTO dto = new DescuentoAplicadoDTO();
        dto.setDescripcion("5% off");
        dto.setPorcentajeDescuento(BigDecimal.valueOf(5).doubleValue());  // Usamos BigDecimal como está en el DTO
        dto.setDescuentoAplicadoTotal(BigDecimal.ZERO.doubleValue());  // Usamos BigDecimal como está en el DTO
        dto.setDescuentoCodigo("CODE5");

        // Convertir el DTO a entidad
        DescuentoAplicado descuentoEntity = DescuentoAplicadoMapper.toEntity(dto);

        // Crear el objeto DescuentoAplicado para simular la respuesta guardada
        DescuentoAplicado descuentoGuardado = new DescuentoAplicado();
        descuentoGuardado.setDescuentoAplicadoId(1L);  // Establecemos el ID de la entidad
        descuentoGuardado.setDescripcion("5% off");
        descuentoGuardado.setPorcentajeDescuento(5.0);  // Usamos Double en lugar de BigDecimal
        descuentoGuardado.setDescuentoAplicadoTotal(0.0);  // Usamos Double en lugar de BigDecimal
        descuentoGuardado.setDescuentoCodigo("CODE5");

        // Crear un Pedido y asociarlo al DescuentoAplicado
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);  // Establecer el ID de pedido
        descuentoGuardado.setPedido(pedido);  // Asociar el pedido al descuento

        // Mockear el comportamiento del repositorio para guardar la entidad
        when(descuentoAplicadoRepository.save(any(DescuentoAplicado.class))).thenReturn(descuentoGuardado);

        // Llamar al servicio para crear el descuento
        DescuentoAplicadoDTO resultado = descuentoAplicadoServiceImpl.crearDescuentoAplicado(dto);

        // Verificar que el resultado no es null y que los valores son los esperados
        assertThat(resultado).isNotNull();
        assertThat(resultado.getDescripcion()).isEqualTo("5% off");
        assertThat(resultado.getPorcentajeDescuento()).isEqualTo(Double.valueOf(5));  // Verificar que el porcentaje coincide
    }

    @Test
    void testEliminarDescuentoAplicado() {
        descuentoAplicadoServiceImpl.eliminarDescuentoAplicado(1L);

        verify(descuentoAplicadoRepository, times(1)).deleteById(1L);
    }
}
