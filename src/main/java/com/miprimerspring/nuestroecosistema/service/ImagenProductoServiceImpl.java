package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.repository.ImagenProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenProductoServiceImpl implements ImagenProductoService {

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Override
    public List<ImagenProducto> obtenerTodasLasImagenes() {
        return imagenProductoRepository.findAll();  // Devuelve todas las imágenes de productos
    }

    @Override
    public ImagenProducto obtenerImagenPorId(Long id) {
        Optional<ImagenProducto> imagenProducto = imagenProductoRepository.findById(id);
        return imagenProducto.orElse(null);  // Devuelve la imagen si existe, o null si no
    }

    @Override
    public ImagenProducto crearImagenProducto(ImagenProducto imagenProducto) {
        return imagenProductoRepository.save(imagenProducto);  // Guarda la imagen de producto
    }

    @Override
    public ImagenProducto actualizarImagenProducto(Long id, ImagenProducto imagenProducto) {
        if (imagenProductoRepository.existsById(id)) {
            imagenProducto.setImagenId(id);  // Establece el ID de la imagen a actualizar
            return imagenProductoRepository.save(imagenProducto);  // Actualiza la imagen
        }
        return null;  // Si no existe la imagen, devuelve null
    }

    @Override
    public void eliminarImagenProducto(Long id) {
        if (imagenProductoRepository.existsById(id)) {
            imagenProductoRepository.deleteById(id);  // Elimina la imagen por su ID
        }
    }
}
