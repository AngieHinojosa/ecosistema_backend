package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.mapper.ProductoMapper;
import com.miprimerspring.nuestroecosistema.model.Producto;
import com.miprimerspring.nuestroecosistema.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Integer id) {
        return productoRepository.findById(Long.valueOf(id))
                .map(productoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorVendedor(Integer vendedorId) {
        List<Producto> productos = productoRepository.findByVendedor_VendedorId(vendedorId);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId) {
        List<Producto> productos = productoRepository.findByCategoria_CategoriaId(categoriaId);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorEstado(String estado) {
        List<Producto> productos = productoRepository.buscarPorEstado(estado);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) {
        List<Producto> productos = productoRepository.findByProductoNombreContaining(nombre);
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> obtenerTodosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(productoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO actualizarProducto(Integer id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        productoMapper.toEntity(productoDTO);  // Mapear DTO a entidad y actualizar
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    @Override
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(Long.valueOf(id));
    }
}