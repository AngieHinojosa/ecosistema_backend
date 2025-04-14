package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CategoriaDTO;
import com.miprimerspring.nuestroecosistema.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO obtenerCategoriaPorId(Long id);

    List<CategoriaDTO> obtenerTodasCategorias();

    CategoriaDTO actualizarCategoria(Long id, CategoriaDTO categoriaDTO);

    void eliminarCategoria(Long id);
}
