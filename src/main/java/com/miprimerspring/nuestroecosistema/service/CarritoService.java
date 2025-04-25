package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.dto.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    CarritoDTO crearCarrito(CarritoDTO carritoDTO);

    CarritoDTO obtenerCarritoPorId(Long id);

    List<CarritoDTO> obtenerCarritosPorUsuario(Long usuarioId);

    List<CarritoDTO> obtenerTodosCarritos();

    CarritoDTO actualizarCarrito(Long carritoId, CarritoDTO carritoDTO);

    void eliminarCarrito(Long carritoId);

    CarritoDTO agregarProductoAlCarrito(Long carritoId, Long productoId, Integer cantidad);

    CarritoDTO eliminarProductoDelCarrito(Long carritoId, Long productoId);

    Double calcularTotalCarrito(Long carritoId);

    boolean pagarCarrito(Long carritoId, String metodoPago);
}
