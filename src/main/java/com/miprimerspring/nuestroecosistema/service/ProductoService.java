package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.ProductoDTO;
import com.miprimerspring.nuestroecosistema.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO obtenerProductoPorId(Integer id);
    List<ProductoDTO> obtenerProductosPorCategoria(Integer categoriaId);
    List<ProductoDTO> obtenerProductosPorNombre(String nombre);
    List<ProductoDTO> obtenerProductosPorUsuario(Long usuarioId);
    List<ProductoDTO> obtenerTodosProductos();
    ProductoDTO actualizarProducto(Integer id, ProductoDTO productoDTO);
    void eliminarProducto(Integer id);
}
