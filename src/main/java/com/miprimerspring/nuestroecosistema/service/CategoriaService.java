package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> obtenerTodasLasCategorias();

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    Categoria crearCategoria(Categoria categoria);

    Categoria actualizarCategoria(Long id, Categoria categoria);

    void eliminarCategoria(Long id);
}
