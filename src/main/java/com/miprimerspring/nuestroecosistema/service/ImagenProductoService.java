package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.ImagenProducto;

import java.util.List;

public interface ImagenProductoService {

    // Obtener todas las imágenes de productos
    List<ImagenProducto> obtenerTodasLasImagenes();

    // Obtener una imagen por su ID
    ImagenProducto obtenerImagenPorId(Long id);

    // Crear una nueva imagen de producto
    ImagenProducto crearImagenProducto(ImagenProducto imagenProducto);

    // Actualizar una imagen de producto
    ImagenProducto actualizarImagenProducto(Long id, ImagenProducto imagenProducto);

    // Eliminar una imagen de producto
    void eliminarImagenProducto(Long id);
}
