package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    Carrito crearCarrito(Carrito carrito);

    Optional<Carrito> obtenerCarritoPorId(Long carritoId);

    List<Carrito> obtenerTodosLosCarritos();

    void eliminarCarrito(Long carritoId);

    Carrito actualizarCarrito(Long carritoId, Carrito carrito);

    List<Carrito> obtenerCarritosPorUsuario(Long usuarioId);
}
