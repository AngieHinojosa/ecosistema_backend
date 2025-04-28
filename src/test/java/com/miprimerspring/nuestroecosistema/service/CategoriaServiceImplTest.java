package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CategoriaDTO;
import com.miprimerspring.nuestroecosistema.mapper.CategoriaMapper;
import com.miprimerspring.nuestroecosistema.model.Categoria;
import com.miprimerspring.nuestroecosistema.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaServiceImpl;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @Mock
    private CategoriaServiceImpl categoriaService;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(CategoriaRepository.class);
        categoriaMapper = mock(CategoriaMapper.class);
        categoriaService = new CategoriaServiceImpl(categoriaRepository, categoriaMapper);
    }

    @Test
    void testCrearCategoria() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoriaNombre("Electrónica");

        Categoria categoria = new Categoria();
        categoria.setCategoriaNombre("Electrónica");

        when(categoriaMapper.toEntity(categoriaDTO)).thenReturn(categoria);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO resultado = categoriaService.crearCategoria(categoriaDTO);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCategoriaNombre()).isEqualTo("Electrónica");
    }

    @Test
    void testObtenerCategoriaPorId() {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(1L);
        categoria.setCategoriaNombre("Libros");

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setCategoriaId(1L);
        categoriaDTO.setCategoriaNombre("Libros");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO resultado = categoriaService.obtenerCategoriaPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCategoriaNombre()).isEqualTo("Libros");
    }


    @Test
    void testActualizarCategoria() {
        Categoria categoriaExistente = new Categoria();
        categoriaExistente.setCategoriaId(1L);
        categoriaExistente.setCategoriaNombre("Antiguo nombre");

        CategoriaDTO nuevaCategoriaDTO = new CategoriaDTO();
        nuevaCategoriaDTO.setCategoriaNombre("Nuevo nombre");

        Categoria categoriaActualizada = new Categoria();
        categoriaActualizada.setCategoriaId(1L);
        categoriaActualizada.setCategoriaNombre("Nuevo nombre");

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaExistente));
        when(categoriaMapper.toEntity(nuevaCategoriaDTO)).thenReturn(categoriaActualizada);
        when(categoriaRepository.save(categoriaActualizada)).thenReturn(categoriaActualizada);
        when(categoriaMapper.toDTO(categoriaActualizada)).thenReturn(nuevaCategoriaDTO);

        CategoriaDTO resultado = categoriaService.actualizarCategoria(1L, nuevaCategoriaDTO);

        assertThat(resultado.getCategoriaNombre()).isEqualTo("Nuevo nombre");
    }

    @Test
    void testEliminarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(1L);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        categoriaService.eliminarCategoria(1L);

        verify(categoriaRepository, times(1)).delete(categoria);
    }

}
