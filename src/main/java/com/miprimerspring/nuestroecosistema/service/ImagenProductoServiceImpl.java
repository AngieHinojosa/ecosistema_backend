package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ImagenProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.ImagenProductoMapper;
import com.miprimerspring.nuestroecosistema.model.ImagenProducto;
import com.miprimerspring.nuestroecosistema.repository.ImagenProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImagenProductoServiceImpl implements ImagenProductoService {

    private final ImagenProductoRepository imagenProductoRepository;
    private final ImagenProductoMapper imagenProductoMapper;

    @Autowired
    public ImagenProductoServiceImpl(ImagenProductoRepository imagenProductoRepository, ImagenProductoMapper imagenProductoMapper) {
        this.imagenProductoRepository = imagenProductoRepository;
        this.imagenProductoMapper = imagenProductoMapper;
    }

    @Override
    public ImagenProductoDTO crearImagenProducto(ImagenProductoDTO imagenProductoDTO) {
        ImagenProducto imagenProducto = imagenProductoMapper.toEntity(imagenProductoDTO);
        ImagenProducto savedImagenProducto = imagenProductoRepository.save(imagenProducto);
        return imagenProductoMapper.toDTO(savedImagenProducto);
    }

    @Override
    public ImagenProductoDTO obtenerImagenProductoPorId(Long id) {
        ImagenProducto imagenProducto = imagenProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        return imagenProductoMapper.toDTO(imagenProducto);
    }

    @Override
    public List<ImagenProductoDTO> obtenerImagenesPorProductoId(Integer productoId) {
        List<ImagenProducto> imagenes = imagenProductoRepository.findByProducto_ProductoId(productoId);
        return imagenes.stream()
                .map(imagenProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImagenProductoDTO> obtenerImagenesPorPrincipal(Boolean imagenEsPrincipal) {
        List<ImagenProducto> imagenes = imagenProductoRepository.findByImagenEsPrincipal(imagenEsPrincipal);
        return imagenes.stream()
                .map(imagenProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImagenProductoDTO> obtenerTodasImagenes() {
        List<ImagenProducto> imagenes = imagenProductoRepository.findAll();
        return imagenes.stream()
                .map(imagenProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImagenProductoDTO actualizarImagenProducto(Long id, ImagenProductoDTO imagenProductoDTO) {
        ImagenProducto imagenProductoExistente = imagenProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        imagenProductoExistente = imagenProductoMapper.toEntity(imagenProductoDTO);
        imagenProductoExistente.setImagenId(id);  // Mantener el ID
        ImagenProducto updatedImagenProducto = imagenProductoRepository.save(imagenProductoExistente);
        return imagenProductoMapper.toDTO(updatedImagenProducto);
    }

    @Override
    public void eliminarImagenProducto(Long id) {
        ImagenProducto imagenProducto = imagenProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
        imagenProductoRepository.delete(imagenProducto);
    }
}