package com.miprimerspring.nuestroecosistema.service;

import com.miprimerspring.nuestroecosistema.DTO.CarritoDTO;
import com.miprimerspring.nuestroecosistema.model.Carrito;
import com.miprimerspring.nuestroecosistema.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    Carrito crearCarritoDesdeDTO(CarritoDTO carritoDTO);

    Optional<CarritoDTO> obtenerCarritoDTOPorId(Long carritoId);

    List<CarritoDTO> obtenerTodosLosCarritosDTO();

    void eliminarCarrito(Long carritoId);

    Carrito actualizarCarritoDesdeDTO(Long carritoId, CarritoDTO carritoDTO);

    List<CarritoDTO> obtenerCarritosPorUsuario(Long usuarioId);
}
