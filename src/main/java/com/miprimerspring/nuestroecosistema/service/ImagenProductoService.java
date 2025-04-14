package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.model.ImagenProducto;

import java.util.List;

public interface ImagenProductoService {

    ImagenProductoDTO crearImagenProducto(ImagenProductoDTO imagenProductoDTO);
    ImagenProductoDTO obtenerImagenProductoPorId(Long id);
    List<ImagenProductoDTO> obtenerImagenesPorProductoId(Integer productoId);
    List<ImagenProductoDTO> obtenerImagenesPorPrincipal(Boolean imagenEsPrincipal);
    List<ImagenProductoDTO> obtenerTodasImagenes();
    ImagenProductoDTO actualizarImagenProducto(Long id, ImagenProductoDTO imagenProductoDTO);
    void eliminarImagenProducto(Long id);
}
