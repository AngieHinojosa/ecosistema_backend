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
    CarritoDTO actualizarCarrito(Long id, CarritoDTO carritoDTO);
    void eliminarCarrito(Long id);
}
