package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto saveProducto(Producto producto);
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(int id);
    void deleteProducto(int id);
}
