package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.ImagenProductoMapper;
import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.repository.ImagenProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImagenProductoServiceImplTest {

    @Mock
    private ImagenProductoRepository imagenProductoRepository;

    @Mock
    private ImagenProductoMapper imagenProductoMapper;

    @InjectMocks
    private ImagenProductoServiceImpl imagenProductoService;

    private ImagenProductoDTO imagenProductoDTO;
    private ImagenProducto imagenProducto;

    @BeforeEach
    void setUp() {
        // Crear objetos para los tests
        imagenProductoDTO = new ImagenProductoDTO();
        imagenProductoDTO.setImagenId(1L);
        imagenProductoDTO.setProductoId(101L);  // Producto ID
        imagenProductoDTO.setImagenUrl("http://url-imagen.com");

        // Crear un mock de Producto
        Producto productoMock = new Producto();
        productoMock.setProductoId(101L); // Asignar el ID de producto mockeado

        // Crear ImagenProducto y asignar un Producto
        imagenProducto = new ImagenProducto();
        imagenProducto.setImagenId(1L);
        imagenProducto.setProducto(productoMock);  // Establecer el Producto completo
        imagenProducto.setImagenUrl("http://url-imagen.com");
    }


    @Test
    void testCrearImagenProducto() {
        // Configurar los mocks
        when(imagenProductoMapper.toEntity(imagenProductoDTO)).thenReturn(imagenProducto);
        when(imagenProductoRepository.save(imagenProducto)).thenReturn(imagenProducto);
        when(imagenProductoMapper.toDTO(imagenProducto)).thenReturn(imagenProductoDTO);

        // Ejecutar el método
        ImagenProductoDTO result = imagenProductoService.crearImagenProducto(imagenProductoDTO);

        // Verificar el resultado
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(imagenProductoDTO.getImagenUrl(), result.getImagenUrl(), "La URL de la imagen debe coincidir");
        verify(imagenProductoRepository).save(imagenProducto); // Verificar que se guardó la imagen
    }

    @Test
    void testObtenerImagenProductoPorId() {
        // Configurar el mock
        when(imagenProductoRepository.findById(1L)).thenReturn(Optional.of(imagenProducto));
        when(imagenProductoMapper.toDTO(imagenProducto)).thenReturn(imagenProductoDTO);

        // Ejecutar el método
        ImagenProductoDTO result = imagenProductoService.obtenerImagenProductoPorId(1L);

        // Verificar el resultado
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(imagenProductoDTO.getImagenId(), result.getImagenId(), "El ID de la imagen debe coincidir");
        verify(imagenProductoRepository).findById(1L); // Verificar que se buscó por ID
    }

    @Test
    void testObtenerImagenesPorProductoId() {
        List<ImagenProducto> imagenes = List.of(imagenProducto);
        when(imagenProductoRepository.findByProducto_ProductoId(101)).thenReturn(imagenes);
        when(imagenProductoMapper.toDTO(imagenProducto)).thenReturn(imagenProductoDTO);

        List<ImagenProductoDTO> result = imagenProductoService.obtenerImagenesPorProductoId(101);

        // Verificar que el tamaño de la lista es correcto
        assertNotNull(result, "La lista no debe ser nula");
        assertEquals(1, result.size(), "Debe haber una imagen para el producto");
    }

    @Test
    void testActualizarImagenProducto() {
        // Crear DTO actualizado
        ImagenProductoDTO updatedDTO = new ImagenProductoDTO();
        updatedDTO.setImagenId(1L);
        updatedDTO.setProductoId(101L);  // Producto ID
        updatedDTO.setImagenUrl("http://nueva-url-imagen.com");

        // Crear un mock de Producto
        Producto productoMock = new Producto();
        productoMock.setProductoId(101L); // Asignar el ID de producto mockeado

        // Crear ImagenProducto actualizado y asignar un Producto
        ImagenProducto updatedImagenProducto = new ImagenProducto();
        updatedImagenProducto.setImagenId(1L);
        updatedImagenProducto.setProducto(productoMock);  // Asignar el objeto Producto
        updatedImagenProducto.setImagenUrl("http://nueva-url-imagen.com");

        // Configurar los mocks
        when(imagenProductoRepository.findById(1L)).thenReturn(Optional.of(imagenProducto));
        when(imagenProductoMapper.toEntity(updatedDTO)).thenReturn(updatedImagenProducto);
        when(imagenProductoRepository.save(updatedImagenProducto)).thenReturn(updatedImagenProducto);
        when(imagenProductoMapper.toDTO(updatedImagenProducto)).thenReturn(updatedDTO);

        // Ejecutar el método
        ImagenProductoDTO result = imagenProductoService.actualizarImagenProducto(1L, updatedDTO);

        // Verificar que la imagen se actualizó correctamente
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(updatedDTO.getImagenUrl(), result.getImagenUrl(), "La URL de la imagen debe haberse actualizado");
    }


    @Test
    void testEliminarImagenProducto() {
        // Configurar el mock
        when(imagenProductoRepository.findById(1L)).thenReturn(Optional.of(imagenProducto));

        // Ejecutar el método
        imagenProductoService.eliminarImagenProducto(1L);

        // Verificar que se llamó al método delete
        verify(imagenProductoRepository).delete(imagenProducto);
    }
}

