package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.ProductoMapper;
import com.miprimerspring.nuestroecosistema.model.ERol;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.model.Usuario;
import com.miprimerspring.nuestroecosistema.repository.ProductoRepository;
import com.miprimerspring.nuestroecosistema.repository.UsuarioRepository;
import com.miprimerspring.nuestroecosistema.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceImplTest {

    private ProductoServiceImpl productoService;
    private ProductoRepository productoRepository;
    private ProductoMapper productoMapper;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        productoRepository = mock(ProductoRepository.class);
        productoMapper = mock(ProductoMapper.class);
        usuarioRepository = mock(UsuarioRepository.class);
        productoService = new ProductoServiceImpl(productoRepository, productoMapper, usuarioRepository);
    }

    // Test para crear un producto
    @Test
    void crearProducto_ShouldReturnProductoDTO() {
        // Datos de entrada
        ProductoDTO productoDTO = new ProductoDTO();
        Producto producto = new Producto();
        Producto savedProducto = new Producto();

        // Usuario con rol vendedor
        Usuario usuario = new Usuario();
        usuario.setRoles(Collections.singleton(ERol.ROLE_VENDEDOR));

        // Mock del proceso de autenticación
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(userDetails.getUsuarioId()).thenReturn(1L);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock de repositorios
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(productoMapper.toEntity(productoDTO)).thenReturn(producto);
        when(productoRepository.save(producto)).thenReturn(savedProducto);
        when(productoMapper.toDTO(savedProducto)).thenReturn(productoDTO);

        // Ejecutar el método
        ProductoDTO result = productoService.crearProducto(productoDTO);

        assertNotNull(result);
        verify(productoRepository, times(1)).save(producto);
    }

    // Test para obtener un producto por ID (exitoso)
    @Test
    void obtenerProductoPorId_ShouldReturnProductoDTO() {
        Integer id = 1;
        Producto producto = new Producto();
        ProductoDTO productoDTO = new ProductoDTO();

        when(productoRepository.findById(Long.valueOf(id))).thenReturn(Optional.of(producto));
        when(productoMapper.toDTO(producto)).thenReturn(productoDTO);

        ProductoDTO result = productoService.obtenerProductoPorId(id);

        assertNotNull(result);
        verify(productoRepository, times(1)).findById(Long.valueOf(id));
    }

    // Test para obtener productos por categoría
    @Test
    void obtenerProductosPorCategoria_ShouldReturnProductoDTOList() {
        Integer categoriaId = 1;
        Producto producto = new Producto();
        List<Producto> productos = Collections.singletonList(producto);
        ProductoDTO productoDTO = new ProductoDTO();

        when(productoRepository.findByCategoria_CategoriaId(categoriaId)).thenReturn(productos);
        when(productoMapper.toDTO(producto)).thenReturn(productoDTO);

        List<ProductoDTO> result = productoService.obtenerProductosPorCategoria(categoriaId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(productoRepository, times(1)).findByCategoria_CategoriaId(categoriaId);
    }
}
